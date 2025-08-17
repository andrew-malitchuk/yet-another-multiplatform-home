package dev.yamh.io.data.ghome.ghome.foobar.util

import com.google.home.DeviceType
import com.google.home.DeviceTypeFactory
import com.google.home.Trait
import com.google.home.TraitFactory
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import dev.yamh.io.data.ghome.ghome.core.Configure
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.LevelDeviceAttribute
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.OnOffDeviceAttribute
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute
import dev.yamh.io.data.ghome.ghome.foobar.device.control.OnOffLightDeviceControl
import dev.yamh.io.data.ghome.ghome.foobar.device.control.base.DeviceControl


public fun getDeviceType(
    deviceType: List<DeviceType>?,
    supportedType: List<DeviceTypeFactory<out DeviceType>>
): List<DeviceControl> {
    val result = mutableListOf<DeviceControl>()
    deviceType?.forEach { originalDeviceType ->
        supportedType.forEach { supportedDeviceType ->
            if (originalDeviceType.factory.factory::class == supportedDeviceType.factory::class) {
                when (originalDeviceType) {
                    is OnOffLightDevice -> {
                        result.add(
                            OnOffLightDeviceControl(
                                getDeviceControls(
                                    deviceTrait = originalDeviceType.traits().toList(),
                                    supportedTrait = Configure.supportedTraits
                                )
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
): List<DeviceAttribute> {
    val result = mutableListOf<DeviceAttribute>()
    deviceTrait?.forEach { originalDeviceTrait ->
        supportedTrait.forEach { supportedDeviceTrait ->
            if (originalDeviceTrait.factory.factory == supportedDeviceTrait.factory) {
                when (originalDeviceTrait) {
                    is OnOff ->
                        result.add(OnOffDeviceAttribute())
                    is LevelControl ->
                        result.add(LevelDeviceAttribute())
                }

            }

        }

    }
    return result.toList()
}