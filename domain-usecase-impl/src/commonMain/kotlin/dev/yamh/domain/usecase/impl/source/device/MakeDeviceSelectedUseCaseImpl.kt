package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.MakeDeviceSelectedUseCase

public class MakeDeviceSelectedUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : MakeDeviceSelectedUseCase {
    override suspend fun invoke(
        device: DeviceEntity,
        isSelected: Boolean
    ): Optional {
        return resultLauncher {
            device.roomId?.let {
                when (isSelected) {
                    false -> deviceRepository.unlinkDevice(
                        device = device,
                        roomId = it
                    )
                    true -> deviceRepository.linkDevice(
                        device = device,
                        roomId = it,
                    )
                }
            }
        }
    }
}