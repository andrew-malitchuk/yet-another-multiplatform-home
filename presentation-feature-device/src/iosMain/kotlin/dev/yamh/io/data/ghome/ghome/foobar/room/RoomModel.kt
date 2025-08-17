package dev.yamh.io.data.ghome.ghome.foobar.room

import dev.yamh.io.data.ghome.ghome.device.DeviceEntity
import dev.yamh.io.data.ghome.ghome.foobar.core.Id

public actual class RoomModel {
    public actual suspend fun getDevices(): List<DeviceEntity> {
        TODO("Not yet implemented")
    }

    public actual suspend fun getDevice(id: Id): DeviceEntity? {
        TODO("Not yet implemented")
    }

    public actual suspend fun device(id: Id): DeviceEntity? {
        TODO("Not yet implemented")
    }
}