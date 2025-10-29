package dev.yamh.io.data.ghome.source.datasource.room

import dev.yamh.common.core.core.Color
import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.io.data.ghome.source.datasource.device.DeviceModel

public expect class RoomModel(id: Id, name: Name, homeId: Id, color: Color) {
    public val id: Id
    public val name: Name
    public val homeId: Id
    public val color: Color

    public suspend fun getDevices(): List<DeviceModel>
    public suspend fun getDevice(id: Id): DeviceModel?
    public suspend fun device(id: Id): DeviceModel?
}