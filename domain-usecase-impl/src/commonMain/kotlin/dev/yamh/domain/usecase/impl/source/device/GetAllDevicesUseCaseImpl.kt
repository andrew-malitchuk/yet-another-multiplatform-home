package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.GetAllDevicesUseCase


public class GetAllDevicesUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : GetAllDevicesUseCase {
    override suspend fun invoke(): Result<List<DeviceEntity>> = resultLauncher {
        val remoteDevices = deviceRepository.getDevices()
        val mergedDevices = mutableListOf<DeviceEntity>()
        remoteDevices.forEach {
            val localDevice = deviceRepository.loadDevice(it.id)
            mergedDevices.add(
                it.copy(
                    customType = localDevice?.customType
                )
            )

        }
        mergedDevices.sortBy { it.id.value }
        mergedDevices
    }
}
