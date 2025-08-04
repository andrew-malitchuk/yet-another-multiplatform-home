package dev.yamh.io.ghome.device.attribute

import dev.yamh.io.ghome.client.HomeClientEntity
import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute

public expect class OnOffDeviceAttribute : DeviceAttribute {

    public companion object {
        context(HomeClientEntity)
        public suspend fun DeviceEntity.turnOn()
        context(HomeClientEntity)
        public suspend fun DeviceEntity.turnOff()
    }
}

