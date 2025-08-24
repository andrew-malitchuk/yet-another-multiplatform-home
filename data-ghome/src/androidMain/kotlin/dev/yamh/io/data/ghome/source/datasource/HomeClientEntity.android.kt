package dev.yamh.io.data.ghome.source.datasource

import com.google.home.DeviceTypeFactory
import com.google.home.HomeClient
import com.google.home.TraitFactory
import com.google.home.matter.standard.BooleanState
import com.google.home.matter.standard.ColorControl
import com.google.home.matter.standard.ColorControlTrait
import com.google.home.matter.standard.ContactSensorDevice
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.LevelControlTrait
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import com.google.home.matter.standard.TemperatureMeasurement
import com.google.home.matter.standard.TemperatureSensorDevice
import dev.yamh.common.core.core.Id
import dev.yamh.io.data.ghome.core.ext.getDeviceAttribute
import dev.yamh.io.data.ghome.core.ext.getDeviceTrait
import dev.yamh.io.data.ghome.core.ext.getRoom
import dev.yamh.io.data.ghome.core.ext.getRoomsFlow
import dev.yamh.io.data.ghome.core.ext.getStructure
import dev.yamh.io.data.ghome.core.ext.getStructureFlow
import dev.yamh.io.data.ghome.core.ext.getStructures
import dev.yamh.io.data.ghome.core.ext.getStructuresFlow
import dev.yamh.io.data.ghome.core.ext.subscribeToDeviceChanges
import dev.yamh.io.data.ghome.source.datasource.device.attribute.AttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.ColorControlAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.ContactAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.DimmableAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.OnOffAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.TemperatureAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType
import dev.yamh.io.data.ghome.source.datasource.home.HomeModel
import dev.yamh.io.data.ghome.source.datasource.home.HomeModel.Companion.toModel
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel.Companion.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.collections.map
import kotlin.getValue
import kotlin.reflect.KClass

public actual class HomeClientModel : KoinComponent {

    public actual constructor()

    public val nativeHomeClient: HomeClient by inject()

    public actual suspend fun getHomes(): List<HomeModel> {
        return nativeHomeClient.getStructures().map { it.toModel() }
    }

    public actual fun getHomesFlow(): Flow<List<HomeModel>> {
        return nativeHomeClient.getStructuresFlow()
            .map { it.map { structure -> structure.toModel() } }
    }

    public actual suspend fun getHome(id: Id): HomeModel? {
        return nativeHomeClient.getStructure(id.value)?.toModel()
    }

    public actual fun getHomeFlow(id: Id): Flow<HomeModel?> {
        return nativeHomeClient.getStructureFlow(id.value)
            .map { structure -> structure?.toModel() }
    }

    public actual suspend fun home(id: Id): HomeModel? {
        return getHome(id)
    }

    public actual fun homeFlow(id: Id): Flow<HomeModel?> {
        return getHomeFlow(id)
    }

    public actual fun getRoomFlow(id: Id): Flow<RoomModel?> {
        return nativeHomeClient.getRoomsFlow(id.value).map { room ->
            room?.toModel()
        }
    }

    public actual suspend fun getRoom(id: Id): RoomModel? {
        return nativeHomeClient.getRoom(id.value)?.toModel()
    }

    public actual suspend fun subscribeAttributeChanges(
        id: Id,
        type: DeviceType?
    ): Flow<AttributeModel> {
        type ?: return emptyFlow()

        val originalControl: DeviceTypeFactory<*> = when (type) {
            DeviceType.OnOff -> OnOffLightDevice
            DeviceType.Dimmable -> OnOffLightDevice
            DeviceType.ColorControl -> OnOffLightDevice
            DeviceType.TemperatureSensor -> TemperatureSensorDevice
            DeviceType.Contact -> ContactSensorDevice
        }

        val originalTrait: TraitFactory<*>? = when (type) {
            DeviceType.Dimmable -> LevelControl
            DeviceType.OnOff -> OnOff
            DeviceType.ColorControl -> ColorControl
            DeviceType.TemperatureSensor -> TemperatureMeasurement
            DeviceType.Contact -> BooleanState
        }

        return nativeHomeClient.subscribeToDeviceChanges(
            id.value,
            originalControl,
            originalTrait
        ).map { trait ->
            when (type) {
                DeviceType.OnOff -> {
                    OnOffAttributeModel(
                        (trait as? OnOff)?.onOff == true
                    )
                }

                DeviceType.Dimmable -> {
                    DimmableAttributeModel(
                        (trait as? LevelControl)?.currentLevel?.toInt() ?: 0
                    )
                }

                DeviceType.ColorControl -> {
                    ColorControlAttributeModel()
                }

                DeviceType.TemperatureSensor -> {
                    TemperatureAttributeModel(
                        "%.2f".format(
                            ((trait as? TemperatureMeasurement)?.measuredValue?.toFloat()
                                ?: 0f) / 100f
                        )
                    )
                }

                DeviceType.Contact -> {
                    ContactAttributeModel(
                        (trait as? BooleanState)?.stateValue == true
                    )
                }
            }

        }
    }


    public actual suspend fun changeDeviceAttribute(
        id: Id,
        type: DeviceType?,
        attribute: AttributeModel
    ) {

        type ?: return

        val originalAttribute: KClass<*> = when (type) {
            DeviceType.Dimmable -> LevelControl::class
            DeviceType.OnOff -> OnOff::class
            DeviceType.ColorControl -> ColorControl::class
            DeviceType.TemperatureSensor -> TemperatureMeasurement::class
            DeviceType.Contact -> BooleanState::class
        }


        val trait =
            this@HomeClientModel.nativeHomeClient.getDeviceTrait(id.value, originalAttribute)

        when (type) {
            DeviceType.OnOff -> {
                (trait as? OnOff)?.let {
                    if (attribute is OnOffAttributeModel) {
                        if (attribute.isOn) {
                            it.on()
                        } else {
                            it.off()
                        }
                    }
                }
            }

            DeviceType.Dimmable -> {
                (trait as? LevelControl)?.let {
                    if (attribute is DimmableAttributeModel) {
                        it.moveToLevelWithOnOff(
                            level = attribute.level.toUByte(),
                            transitionTime = null,
                            optionsMask = LevelControlTrait.OptionsBitmap(),
                            optionsOverride = LevelControlTrait.OptionsBitmap()
                        )
                    }
                }
            }

            DeviceType.ColorControl -> {
                (trait as? ColorControl)?.let {
                    if (attribute is ColorControlAttributeModel) {

                        val min = it.colorTempPhysicalMinMireds
                        val max = it.colorTempPhysicalMaxMireds

                        if (min == null || max == null) {
                            return
                        }

                        val foo = (min..max).random().toUShort()

                        it.moveToColorTemperature(
                            colorTemperatureMireds = foo,
                            transitionTime = 10u, // ~1s, одиниці — часто deciseconds
                            optionsMask = ColorControlTrait.OptionsBitmap(),
                            optionsOverride = ColorControlTrait.OptionsBitmap()
                        )
                    }
                }
            }

            DeviceType.TemperatureSensor -> Unit
            DeviceType.Contact -> Unit
        }

    }

    public actual suspend fun getDeviceAttribute(id: Id, type: DeviceType?): AttributeModel? {
        type ?: return null

        val originalControl: DeviceTypeFactory<*> = when (type) {
            DeviceType.OnOff -> OnOffLightDevice
            DeviceType.Dimmable -> OnOffLightDevice
            DeviceType.ColorControl -> OnOffLightDevice
            DeviceType.TemperatureSensor -> TemperatureSensorDevice
            DeviceType.Contact -> ContactSensorDevice
        }

        val originalTrait: TraitFactory<*>? = when (type) {
            DeviceType.Dimmable -> LevelControl
            DeviceType.OnOff -> OnOff
            DeviceType.ColorControl -> ColorControl
            DeviceType.TemperatureSensor -> TemperatureMeasurement
            DeviceType.Contact -> BooleanState
        }

        return nativeHomeClient.getDeviceAttribute(
            id.value,
            originalControl,
            originalTrait
        ).let { trait ->
            when (type) {
                DeviceType.OnOff -> {
                    OnOffAttributeModel(
                        (trait as? OnOff)?.onOff == true
                    )
                }

                DeviceType.Dimmable -> {
                    DimmableAttributeModel(
                        (trait as? LevelControl)?.currentLevel?.toInt() ?: 0
                    )
                }

                DeviceType.ColorControl -> {
                    ColorControlAttributeModel()
                }

                DeviceType.TemperatureSensor -> {
                    TemperatureAttributeModel(
                        "%.2f".format(
                            ((trait as? TemperatureMeasurement)?.measuredValue?.toFloat()
                                ?: 0f) / 100f
                        )
                    )
                }

                DeviceType.Contact -> {
                    ContactAttributeModel(
                        (trait as? BooleanState)?.stateValue == true
                    )
                }
            }

        }
    }


}