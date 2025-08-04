package dev.yamh.io.ghome.device.control.base

import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute

public abstract class DeviceControl {
    public abstract var attributes: List<DeviceAttribute>
}