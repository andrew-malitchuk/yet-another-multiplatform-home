package dev.yamh.io.ghome.device.control

import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute
import dev.yamh.io.ghome.device.control.base.DeviceControl

public data class OnOffLightDeviceControl(
    override var attributes: List<DeviceAttribute>
) : DeviceControl()