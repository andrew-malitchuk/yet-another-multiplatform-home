package dev.yamh.io.data.ghome.ghome.foobar.device

import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.core.Name
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute
import dev.yamh.io.data.ghome.ghome.foobar.device.control.base.DeviceControl
import kotlin.reflect.KClass

public expect class DeviceModel(
    id: Id,
    name: Name,
    homeId: Id?,
    roomId: Id?,
    originalId: Id?,
    controls: List<DeviceControl>
) {

    public val id: Id
    public val name: Name
    public val homeId: Id?
    public val roomId: Id?
    public val originalId: Id?

    public val controls: List<DeviceControl>

    public fun containsControlAttribute(type: KClass<out DeviceAttribute>): Boolean

}