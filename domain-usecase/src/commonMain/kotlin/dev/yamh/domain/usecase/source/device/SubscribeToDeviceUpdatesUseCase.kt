package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType

public interface SubscribeToDeviceUpdatesUseCase {
    public suspend operator fun invoke(
        device: DeviceEntity,
        type: DeviceType
    ): kotlinx.coroutines.flow.Flow<Result<AttributeEntity>>
}