package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.GetDevicesByRoomUseCase

public class GetDevicesByRoomUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : GetDevicesByRoomUseCase {
    override suspend fun invoke(room: RoomEntity): Result<List<DeviceEntity>> = resultLauncher {
        val remoteDevices = deviceRepository.getDevicesByRoom(room.id)
        val mergedDevices = mutableListOf<DeviceEntity>()
        remoteDevices.forEach {
            val localDevice = deviceRepository.loadDevice(it.id)
            mergedDevices.add(
                it.copy(
                    customType = localDevice?.customType
                )
            )

        }
        mergedDevices.sortBy { it.name.value }
        mergedDevices
    }
}
