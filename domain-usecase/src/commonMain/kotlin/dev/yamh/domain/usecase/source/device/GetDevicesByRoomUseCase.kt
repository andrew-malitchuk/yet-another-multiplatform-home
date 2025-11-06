package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity

public interface GetDevicesByRoomUseCase {
    public suspend operator fun invoke(room: RoomEntity): Result<List<DeviceEntity>>
}