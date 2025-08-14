package dev.yamh.io.data.ghome.ghome.foobar.device.control

import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute
import dev.yamh.io.data.ghome.ghome.foobar.device.control.base.DeviceControl

public data class OnOffLightDeviceControl(
    override var attributes: List<DeviceAttribute>
) : DeviceControl()