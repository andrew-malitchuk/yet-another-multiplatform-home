package dev.yamh.io.data.ghome.ghome.foobar.device.attribute

import dev.yamh.io.data.ghome.ghome.foobar.HomeClientModel
import dev.yamh.io.data.ghome.ghome.foobar.device.DeviceModel
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute


public expect class OnOffDeviceAttribute : DeviceAttribute {

    public companion object {
        context(HomeClientModel)
        public suspend fun DeviceModel.turnOn()
        context(HomeClientModel)
        public suspend fun DeviceModel.turnOff()
    }
}

