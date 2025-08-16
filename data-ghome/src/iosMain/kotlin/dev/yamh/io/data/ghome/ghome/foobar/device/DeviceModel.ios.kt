package dev.yamh.io.data.ghome.ghome.foobar.device

import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute
import kotlin.reflect.KClass

public actual class DeviceModel actual constructor(
    id: Id,
    name: String,
    homeId: Id,
    roomId: Id,
    agentId: Id
) {
    actual val id: Id
        get() = TODO("Not yet implemented")
    actual val name: String
        get() = TODO("Not yet implemented")
    actual val homeId: Id
        get() = TODO("Not yet implemented")
    actual val roomId: Id
        get() = TODO("Not yet implemented")
    actual val originalId: Id
        get() = TODO("Not yet implemented")
    actual fun containsControlAttribute(type: KClass<out DeviceAttribute>): Boolean {
        TODO("Not yet implemented")
    }
}

