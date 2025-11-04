package dev.yamh.domain.repository.source

import dev.yamh.common.core.core.Id
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.domain.core.source.model.device.type.DeviceType
import kotlinx.coroutines.flow.Flow

public interface DeviceRepository {
    public suspend fun getDevices(): List<DeviceEntity>

    public suspend fun getDevicesByRoom(roomId: Id): List<DeviceEntity>
    public suspend fun loadDevice(id: Id): DeviceEntity?

    public suspend fun linkDevice(device: DeviceEntity, roomId:Id)
    public suspend fun unlinkDevice(device: DeviceEntity, roomId:Id)
    public suspend fun isDeviceSelected(device: DeviceEntity, roomId:Id): Boolean
    public suspend fun loadDevicesByRoom(roomId: Id): List<DeviceEntity>
    public suspend fun setCustomType(device: DeviceEntity, customType: DeviceCustomType)



    public suspend fun subscribeToChanges(
        device: DeviceEntity,
        type: DeviceType
    ): Flow<AttributeEntity>


    public suspend fun getDeviceAttribute(
        device: DeviceEntity,
        type: DeviceType
    ): AttributeEntity?

    public suspend fun changeDeviceAttribute(
        device: DeviceEntity,
        type: DeviceType,
        attribute: AttributeEntity
    ): Unit

}