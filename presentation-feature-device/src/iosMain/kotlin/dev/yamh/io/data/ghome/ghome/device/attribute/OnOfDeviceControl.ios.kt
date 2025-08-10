package dev.yamh.io.data.ghome.ghome.device.attribute

import dev.yamh.io.data.ghome.ghome.client.HomeClientEntity
import dev.yamh.io.data.ghome.ghome.device.DeviceEntity
import dev.yamh.io.data.ghome.ghome.device.attribute.base.DeviceAttribute

actual class OnOffDeviceAttribute : DeviceAttribute() {
    context(DeviceEntity, HomeClientEntity)
    actual suspend fun turnOn() {
    }

    context(DeviceEntity, HomeClientEntity)
    actual suspend fun turnOff() {
    }
}