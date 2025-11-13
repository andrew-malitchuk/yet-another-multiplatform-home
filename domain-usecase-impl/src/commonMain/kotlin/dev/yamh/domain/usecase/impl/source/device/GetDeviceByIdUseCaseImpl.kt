package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.common.core.core.Id
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.GetDeviceByIdUseCase
import dev.yamh.domain.usecase.source.device.GetDevicesByRoomUseCase

public class GetDeviceByIdUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : GetDeviceByIdUseCase {
    override suspend fun invoke(id: Id): Result<DeviceEntity?> {
        return resultLauncher {
            deviceRepository.loadDevice(id)
        }
    }
}
