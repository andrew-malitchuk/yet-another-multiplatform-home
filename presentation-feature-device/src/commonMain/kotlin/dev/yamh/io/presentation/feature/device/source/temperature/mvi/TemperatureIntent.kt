package dev.yamh.io.presentation.feature.device.source.temperature.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class TemperatureIntent : MviIntent {
    public data class Setup(val device: DeviceEntity) : TemperatureIntent()
    public data object OnBackClickIntent : TemperatureIntent()
    public data object RefreshIntent : TemperatureIntent()
    public data object GoToMainIntent : TemperatureIntent()
    public data class GoToSettingsIntent(val device: DeviceEntity) : TemperatureIntent()
}