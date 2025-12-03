package dev.yamh.io.presentation.feature.device.source.window

import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.ColorControlAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.DimmableAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.WindowCoveringEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.usecase.source.device.ChangeDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.SubscribeToDeviceUpdatesUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowIntent
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowMviEffect
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowState
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class WindowViewModel(
    private val subscribeToDeviceUpdatesUseCase: SubscribeToDeviceUpdatesUseCase,
    private val changeDeviceAttributeUseCase: ChangeDeviceAttributeUseCase,
    private val getDeviceAttributeUseCase: GetDeviceAttributeUseCase
) :
    BaseViewModel<WindowState, WindowIntent, WindowMviEffect>() {

    override val state: StateFlow<WindowState> = MutableStateFlow(WindowState())

    override val intent: StateFlow<WindowIntent?> = MutableStateFlow(null)

    override val effect: Channel<WindowMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: WindowIntent) {
        when (intent) {

            WindowIntent.OnBackClickIntent -> onEffect(WindowNavigationMviEffect.OnNavBackClickMviEffect)
            is WindowIntent.Setup -> {
                updateState {
                    copy(
                        device = intent.device
                    )
                }
            }

            WindowIntent.GoToMainIntent -> onEffect(WindowNavigationMviEffect.GoToMainMviEffect)
            is WindowIntent.GoToSettingsIntent -> onEffect(
                WindowNavigationMviEffect.GoToSettingsMviEffect(
                    device = intent.device
                )
            )

            is WindowIntent.ChangeAttribute -> {
                changeAttribute(intent.attribute)
            }
        }
    }

    override fun onEffect(effect: WindowMviEffect) {
        executeCoroutine {
            this@WindowViewModel.effect.send(effect)
        }
    }


    private var job: Job? = null

    private fun changeAttribute(attribute: AttributeEntity) {
        val device = state.value.device ?: return
        val type = when (attribute) {
            is DimmableAttributeEntity -> DeviceType.Dimmable
            is OnOffAttributeEntity -> DeviceType.OnOff
            is ColorControlAttributeEntity -> DeviceType.ColorControl
            is TemperatureAttributeEntity -> DeviceType.TemperatureSensor
            is ContactAttributeEntity -> DeviceType.Contact
            is WindowCoveringEntity -> DeviceType.WindowCovering
        }
        job?.cancel()
        job = null
        job = executeCoroutine(
            debounce = 1_000L,
            loading = {
                updateState {
                    copy(isLoading = it)
                }
            }
        ) {
            changeDeviceAttributeUseCase(
                device,
                type,
                attribute
            )
        }
    }
}


