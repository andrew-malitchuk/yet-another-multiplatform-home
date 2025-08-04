package dev.yamh.io.data.ghome.ghome.device.control

import dev.yamh.io.data.ghome.ghome.device.attribute.base.DeviceAttribute
import dev.yamh.io.data.ghome.ghome.device.control.base.DeviceControl

public data class OnOffLightDeviceControl(
    override var attributes: List<DeviceAttribute>
) : DeviceControl()