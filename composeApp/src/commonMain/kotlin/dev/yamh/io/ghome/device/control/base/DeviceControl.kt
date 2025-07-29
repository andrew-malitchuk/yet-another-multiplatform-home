package dev.yamh.io.ghome.device.control.base

import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute

abstract class DeviceControl {
    abstract var attributes: List<DeviceAttribute>
}