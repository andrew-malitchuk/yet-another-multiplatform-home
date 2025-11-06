package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.usecase.core.monad.Optional

public interface ChangeDeviceAttributeUseCase {
    public suspend operator fun invoke(
        device: DeviceEntity,
        type: DeviceType,
        attribute: AttributeEntity
    ): Optional
}