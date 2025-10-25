package dev.yamh.io.data.ghome.source.datasource.device

import com.google.home.HomeDevice
import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.io.data.ghome.source.core.Configure
import dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType
import dev.yamh.io.data.ghome.source.datasource.util.getDeviceType
import kotlinx.coroutines.flow.firstOrNull

public actual class DeviceModel actual constructor(
    id: Id,
    name: Name,
    homeId: Id?,
    roomId: Id?,
    originalId: Id?,
    type: List<DeviceType>
) {

    public actual val id: Id = id
    public actual val name: Name = name
    public actual val homeId: Id? = homeId
    public actual val roomId: Id? = roomId
    public actual val originalId: Id? = originalId
    public actual val type: List<DeviceType> = type

    internal companion object Companion {
        internal suspend fun HomeDevice.toNewModel(): DeviceModel {
            return DeviceModel(
                id = Id(this.id.id),
                name = Name(name),
                homeId = structureId?.id?.let { Id(it) },
                roomId = roomId?.id?.let { Id(it) },
                originalId = agentId?.id?.let { Id(it) },
                type = getDeviceType(
                    deviceType = this.types().firstOrNull()?.toList() ?: emptyList(),
                    supportedType = Configure.supportedTypes
                ).distinct()
            )
        }
    }
}