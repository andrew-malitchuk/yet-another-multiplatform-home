package dev.yamh.io.ghome.device

import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute
import dev.yamh.io.ghome.device.control.base.DeviceControl
import kotlin.reflect.KClass

public data class DeviceEntity(
    val id: String,
    val name: String,
    val isMatterDevice: Boolean,
    val isInStructure: Boolean,
    val isInRoom: Boolean,
    val roomId: String?,
    val structureId: String?,
    val agentId: String?,
    val controls: List<DeviceControl>
) {
    public fun getControlByType(type: KClass<DeviceControl>): DeviceControl? {
        return controls.firstOrNull { type.isInstance(it) }
    }

    public fun containsControlType(type: KClass<out DeviceControl>): Boolean {
        return controls.any { type.isInstance(it) }
    }

    public fun containsControlAttribute(type: KClass<out DeviceAttribute>): Boolean {
        return controls.any { it.attributes.any { type.isInstance(it) } }
    }

}