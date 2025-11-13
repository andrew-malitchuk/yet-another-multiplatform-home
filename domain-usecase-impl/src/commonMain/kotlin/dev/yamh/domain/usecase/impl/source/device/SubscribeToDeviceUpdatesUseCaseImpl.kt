package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.usecase.source.device.SubscribeToDeviceUpdatesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class SubscribeToDeviceUpdatesUseCaseImpl(
    private val deviceRepository: DeviceRepository
) : SubscribeToDeviceUpdatesUseCase {
    override suspend fun invoke(
        device: DeviceEntity,
        type: DeviceType
    ): Flow<Result<AttributeEntity>> =
        deviceRepository.subscribeToChanges(device, type).map { device ->
            runCatching {
                device
            }
        }
}