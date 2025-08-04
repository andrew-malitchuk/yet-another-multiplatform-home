package dev.yamh.io.data.ghome.ghome.device.attribute

import dev.yamh.io.data.ghome.ghome.client.HomeClientEntity
import dev.yamh.io.data.ghome.ghome.device.DeviceEntity
import dev.yamh.io.data.ghome.ghome.device.attribute.base.DeviceAttribute

public expect class OnOffDeviceAttribute : DeviceAttribute {

    public companion object {
        context(HomeClientEntity)
        public suspend fun DeviceEntity.turnOn()
        context(HomeClientEntity)
        public suspend fun DeviceEntity.turnOff()
    }
}

