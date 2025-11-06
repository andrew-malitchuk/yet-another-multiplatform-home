package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.domain.usecase.core.monad.Optional

public interface SetCustomDeviceTypeUseCase {
    public suspend operator fun invoke(device: DeviceEntity, customType: DeviceCustomType): Optional
}