package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase

public class GetDeviceAttributeUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : GetDeviceAttributeUseCase {
    override suspend fun invoke(
        device: DeviceEntity,
        type: DeviceType
    ): Result<AttributeEntity?> = resultLauncher {
        deviceRepository.getDeviceAttribute(device, type)
    }
}