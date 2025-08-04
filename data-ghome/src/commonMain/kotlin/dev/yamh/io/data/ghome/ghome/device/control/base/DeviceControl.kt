package dev.yamh.io.data.ghome.ghome.device.control.base

import dev.yamh.io.data.ghome.ghome.device.attribute.base.DeviceAttribute

public abstract class DeviceControl {
    public abstract var attributes: List<DeviceAttribute>
}