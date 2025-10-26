package dev.yamh.io.data.ghome.source.datasource.util

import com.google.home.DeviceType
import com.google.home.DeviceTypeFactory
import com.google.home.Trait
import com.google.home.TraitFactory
import com.google.home.matter.standard.BooleanState
import com.google.home.matter.standard.ColorControl
import com.google.home.matter.standard.ColorTemperatureLightDevice
import com.google.home.matter.standard.ContactSensorDevice
import com.google.home.matter.standard.DimmableLightDevice
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import com.google.home.matter.standard.OnOffPluginUnitDevice
import com.google.home.matter.standard.RoomAirConditionerDevice
import com.google.home.matter.standard.TemperatureMeasurement
import com.google.home.matter.standard.TemperatureSensorDevice
import com.google.home.matter.standard.WindowCovering
import com.google.home.matter.standard.WindowCoveringDevice
import dev.yamh.io.data.ghome.source.core.Configure

public fun getDeviceType(
    deviceType: List<DeviceType>?,
    supportedType: List<DeviceTypeFactory<out DeviceType>>
): List<dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType> {
    val result = mutableListOf<dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType>()
    deviceType?.forEach { originalDeviceType ->
        supportedType.forEach { supportedDeviceType ->
            if (originalDeviceType.factory.factory::class == supportedDeviceType.factory::class) {
                // TODO: refactor
                when (originalDeviceType) {
                    is OnOffLightDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is DimmableLightDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is ColorTemperatureLightDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is OnOffPluginUnitDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is TemperatureSensorDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is ContactSensorDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is RoomAirConditionerDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }

                    is WindowCoveringDevice -> {
                        result.addAll(
                            getDeviceControls(
                                deviceTrait = originalDeviceType.traits().toList(),
                                supportedTrait = Configure.supportedTraits
                            )
                        )
                        return@forEach
                    }
                }
            }
        }
    }
    return result
}

public fun getDeviceControls(
    deviceTrait: List<Trait>?,
    supportedTrait: List<TraitFactory<out Trait>>
): List<dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType> {
    val result = mutableListOf<dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType>()
    deviceTrait?.forEach { originalDeviceTrait ->
        supportedTrait.forEach { supportedDeviceTrait ->
            if (originalDeviceTrait.factory.factory == supportedDeviceTrait.factory) {
                when (originalDeviceTrait) {
                    is OnOff ->
                        result.add(dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.OnOff)

                    is LevelControl ->
                        result.add(dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.Dimmable)

                    is ColorControl ->
                        result.add(dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.ColorControl)

                    is TemperatureMeasurement ->
                        result.add(dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.TemperatureSensor)

                    is BooleanState ->
                        result.add(dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.Contact)

                    is WindowCovering ->
                        result.add(dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.WindowCovering)
                }
            }

        }

    }
    return result.toList()
}