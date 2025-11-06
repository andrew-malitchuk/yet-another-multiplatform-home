package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity

public interface IsDeviceSelectedUseCase {
    public suspend operator fun invoke(
        device: DeviceEntity,
    ): Result<Boolean>
}