package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.MakeDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.SetCustomDeviceTypeUseCase


public class SetCustomDeviceTypeUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : SetCustomDeviceTypeUseCase {
    override suspend fun invoke(
        device: DeviceEntity,
        customType: DeviceCustomType
    ): Optional {
        return resultLauncher {
            deviceRepository.setCustomType(
                device = device,
                customType = customType
            )
        }
    }
}