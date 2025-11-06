package dev.yamh.domain.usecase.source.device

import dev.yamh.common.core.core.Id
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity

public interface GetDeviceByIdUseCase {
    public suspend operator fun invoke(id: Id): Result<DeviceEntity?>
}