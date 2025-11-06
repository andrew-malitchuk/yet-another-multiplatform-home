package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.domain.usecase.core.monad.Optional

public interface MakeDeviceSelectedUseCase {
    public suspend operator fun invoke(device: DeviceEntity, isSelected: Boolean): Optional
}