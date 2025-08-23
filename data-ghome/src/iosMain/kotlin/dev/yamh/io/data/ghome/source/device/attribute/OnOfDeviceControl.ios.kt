package dev.yamh.io.data.ghome.source.device.attribute

import dev.yamh.io.data.ghome.source.client.HomeClientEntity
import dev.yamh.io.data.ghome.source.device.DeviceEntity
import dev.yamh.io.data.ghome.source.device.attribute.base.DeviceAttribute

actual class OnOffDeviceAttribute : DeviceAttribute() {
    context(DeviceEntity, HomeClientEntity)
    actual suspend fun turnOn() {
    }

    context(DeviceEntity, HomeClientEntity)
    actual suspend fun turnOff() {
    }
}