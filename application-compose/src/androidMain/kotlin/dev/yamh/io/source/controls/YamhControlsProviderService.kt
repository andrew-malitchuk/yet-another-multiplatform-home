package dev.yamh.io.source.controls

import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.service.controls.Control
import android.service.controls.ControlsProviderService
import android.service.controls.DeviceTypes
import android.service.controls.actions.BooleanAction
import android.service.controls.actions.ControlAction
import android.service.controls.templates.ControlButton
import android.service.controls.templates.ToggleTemplate
import androidx.annotation.RequiresApi
import dev.yamh.data.database.impl.di.dataDatabaseDataStoreModule
import dev.yamh.data.preference.impl.di.dataPreferenceDataStoreModule
import dev.yamh.data.preference.impl.di.dataPreferenceImplModule
import dev.yamh.data.repository.impl.di.dataRepositoryImplDataModule
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.GeneralDeviceType
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.repository.di.domainRepositoryModule
import dev.yamh.domain.usecase.impl.di.domainUseCaseImplModule
import dev.yamh.domain.usecase.source.device.ChangeDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetAllDevicesUseCase
import dev.yamh.io.MainActivity
import dev.yamh.io.data.ghome.di.dataGHomeModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.jdk9.flowPublish
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.Koin
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import java.util.function.Consumer

/**
 * https://developer.android.com/develop/ui/views/device-control?utm_source=chatgpt.com
 */
@RequiresApi(Build.VERSION_CODES.R)
public class YamhControlsProviderService : ControlsProviderService() {

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    private val getAllDevices: GetAllDevicesUseCase by inject()
    private val changeAttribute: ChangeDeviceAttributeUseCase by inject()

    private val controlFlows = mutableMapOf<String, MutableSharedFlow<Control>>()

    private val localState = mutableMapOf<String, Boolean>()

    override fun onCreate() {
        super.onCreate()
        ensureKoin()
    }

    override fun createPublisherForAllAvailable(): java.util.concurrent.Flow.Publisher<Control> =
        flowPublish {

            val devices = getAllDevices().getOrNull().orEmpty()

            devices
                .filter { it.generalType() == GeneralDeviceType.Light || it.generalType() == GeneralDeviceType.Switch }
                .forEach { dev ->
                    val c = Control.StatelessBuilder(dev.id.value, pendingIntent())
                        .setTitle(dev.name.value)
                        .setDeviceType(DeviceTypes.TYPE_LIGHT)
                        .build()

                    send(c)
                }
        }

    override fun createPublisherFor(
        controlIds: List<String>
    ): java.util.concurrent.Flow.Publisher<Control> =
        flowPublish {

            val devices = getAllDevices().getOrNull().orEmpty()
            val flowsToMerge = mutableListOf<MutableSharedFlow<Control>>()

            controlIds.forEach { id ->

                val device = devices.firstOrNull { it.id.value == id }
                if (device == null) {
                    return@forEach
                }

                val flow = controlFlows.getOrPut(id) {
                    MutableSharedFlow(replay = 1)
                }

                val initial = buildControl(device, localState[id])
                send(initial)
                flowsToMerge += flow
            }

            if (flowsToMerge.isNotEmpty()) {
                kotlinx.coroutines.flow.merge(*flowsToMerge.toTypedArray())
                    .collect { updated ->
                        send(updated)
                    }
            }
        }

    override fun performControlAction(
        controlId: String,
        action: ControlAction,
        consumer: Consumer<Int>
    ) {
        if (action !is BooleanAction) {
            consumer.accept(ControlAction.RESPONSE_FAIL)
            return
        }

        val newState = action.newState

        scope.launch {
            val devices = getAllDevices().getOrNull().orEmpty()
            val dev = devices.firstOrNull { it.id.value == controlId } ?: return@launch

            val result = changeAttribute(
                device = dev,
                type = DeviceType.OnOff,
                attribute = OnOffAttributeEntity(newState)
            )

            if (result.isSuccess) {
                localState[controlId] = newState

                emitUpdated(dev)

                consumer.accept(ControlAction.RESPONSE_OK)
            } else {
                consumer.accept(ControlAction.RESPONSE_FAIL)
            }
        }
    }

    private suspend fun emitUpdated(device: DeviceEntity) {
        val flow = controlFlows[device.id.value] ?: return

        val updated = buildControl(
            device = device,
            isOnOverride = localState[device.id.value]
        )

        flow.emit(updated)
    }

    private fun buildControl(device: DeviceEntity, isOnOverride: Boolean?): Control {
        val isOn = isOnOverride ?: (device.attribute as? OnOffAttributeEntity)?.isOn ?: false

        return Control.StatefulBuilder(device.id.value, pendingIntent())
            .setTitle(device.name.value)
            .setSubtitle(if (isOn) "On" else "Off")
            .setDeviceType(DeviceTypes.TYPE_LIGHT)
            .setStatus(Control.STATUS_OK)
            .setControlTemplate(
                ToggleTemplate(
                    device.id.value,
                    ControlButton(isOn, if (isOn) "Turn Off" else "Turn On")
                )
            )
            .build()
    }

    private fun pendingIntent() = PendingIntent.getActivity(
        this, 0,
        Intent(this, MainActivity::class.java),
        PendingIntent.FLAG_IMMUTABLE
    )

    private fun ensureKoin(): Koin {
        return GlobalContext.getOrNull()
            ?: startKoin {
                androidContext(applicationContext)
                modules(
                    dataGHomeModule,
                    dataPreferenceImplModule,
                    dataPreferenceDataStoreModule,
                    dataDatabaseDataStoreModule,
                    dataRepositoryImplDataModule,
                    domainRepositoryModule,
                    domainUseCaseImplModule,
                )
            }.koin
    }
}
