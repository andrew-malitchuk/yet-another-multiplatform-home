package dev.yamh.io.data.ghome.source.datasource.device

import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType


public expect class DeviceModel(
    id: Id,
    name: Name,
    homeId: Id?,
    roomId: Id?,
    originalId: Id?,
    type: List<DeviceType>
) {

    public val id: Id
    public val name: Name
    public val homeId: Id?
    public val roomId: Id?
    public val originalId: Id?

    public val type: List<DeviceType>
}