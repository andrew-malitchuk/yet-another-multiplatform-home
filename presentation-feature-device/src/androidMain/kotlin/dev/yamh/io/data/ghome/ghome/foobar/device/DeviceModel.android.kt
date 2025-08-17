package dev.yamh.io.data.ghome.ghome.foobar.device

import com.google.home.HomeDevice
import dev.yamh.io.data.ghome.ghome.core.Configure
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.core.Name
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute
import dev.yamh.io.data.ghome.ghome.foobar.device.control.base.DeviceControl
import dev.yamh.io.data.ghome.ghome.foobar.util.getDeviceType
import kotlinx.coroutines.flow.firstOrNull
import kotlin.reflect.KClass


public actual class DeviceModel actual constructor(
    id: Id,
    name: Name,
    homeId: Id?,
    roomId: Id?,
    originalId: Id?,
    controls: List<DeviceControl>
) {

    public actual val id: Id = id
    public actual val name: Name = name
    public actual val homeId: Id? = homeId
    public actual val roomId: Id? = roomId
    public actual val originalId: Id? = originalId
    public actual val controls: List<DeviceControl> = controls

    public actual fun containsControlAttribute(type: KClass<out DeviceAttribute>): Boolean {
        return controls.any { it.attributes.any { type.isInstance(it) } }
    }

    internal companion object Companion {
        internal suspend fun HomeDevice.toModel(): DeviceModel {
            return DeviceModel(
                id = Id(this.id.id),
                name = Name(name),
                homeId = structureId?.id?.let { Id(it) },
                roomId = roomId?.id?.let { Id(it) },
                originalId = agentId?.id?.let { Id(it) },
                controls =  getDeviceType(
                    deviceType = this.types().firstOrNull()?.toList()?: emptyList(),
                    supportedType = Configure.supportedTypes
                )
            )
        }
    }

}