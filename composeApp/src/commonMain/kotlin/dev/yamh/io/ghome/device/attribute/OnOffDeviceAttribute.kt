package dev.yamh.io.ghome.device.attribute

import dev.yamh.io.ghome.client.HomeClientEntity
import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute

expect class OnOffDeviceAttribute : DeviceAttribute {

    companion object {
        context(HomeClientEntity)
        suspend fun DeviceEntity.turnOn()
        context(HomeClientEntity)
        suspend fun DeviceEntity.turnOff()
    }
}

