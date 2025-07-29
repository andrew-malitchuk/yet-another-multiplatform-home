package dev.yamh.io.ghome.device.attribute

import dev.yamh.io.ghome.client.HomeClientEntity
import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute

actual class OnOffDeviceAttribute : DeviceAttribute() {
    context(DeviceEntity, HomeClientEntity)
    actual suspend fun turnOn() {
    }

    context(DeviceEntity, HomeClientEntity)
    actual suspend fun turnOff() {
    }
}