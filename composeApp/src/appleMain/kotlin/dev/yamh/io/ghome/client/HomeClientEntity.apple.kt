package dev.yamh.io.ghome.client

import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute

actual inline fun <reified T : DeviceAttribute> HomeClientEntity.control(
    device: DeviceEntity
) {
}