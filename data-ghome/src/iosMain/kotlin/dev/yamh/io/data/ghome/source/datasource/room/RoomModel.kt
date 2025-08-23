package dev.yamh.io.data.ghome.source.datasource.room

import dev.yamh.io.data.ghome.source.device.DeviceEntity

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