package dev.yamh.domain.usecase.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity

public interface GetSelectedDeviceUseCase {
    public suspend operator fun invoke(): Result<List<DeviceEntity>>
}