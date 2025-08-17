package dev.yamh.io.data.ghome.ghome.foobar.room

import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.core.Name
import dev.yamh.io.data.ghome.ghome.foobar.device.DeviceModel

public expect class RoomModel(id: Id, name: Name, homeId: Id) {

    public val id: Id
    public val name: Name
    public val homeId: Id

    public suspend fun getDevices(): List<DeviceModel>
    public suspend fun getDevice(id: Id): DeviceModel?
    public suspend fun device(id: Id): DeviceModel?
}