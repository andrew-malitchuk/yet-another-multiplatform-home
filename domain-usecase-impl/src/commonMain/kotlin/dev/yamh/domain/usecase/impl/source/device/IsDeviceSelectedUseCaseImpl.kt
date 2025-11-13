package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.IsDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.MakeDeviceSelectedUseCase

public class IsDeviceSelectedUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : IsDeviceSelectedUseCase {
    override suspend fun invoke(device: DeviceEntity): Result<Boolean> = resultLauncher {
        device.roomId?.let {
            deviceRepository.isDeviceSelected(
                device = device,
                roomId = it
            )
        } ?: false
    }
}