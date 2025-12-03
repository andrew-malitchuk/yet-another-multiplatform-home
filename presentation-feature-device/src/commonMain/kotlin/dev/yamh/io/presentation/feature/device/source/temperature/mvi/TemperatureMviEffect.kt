package dev.yamh.io.presentation.feature.device.source.temperature.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface TemperatureMviEffect : MviEffect

public sealed class TemperatureNavigationMviEffect : TemperatureMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : TemperatureNavigationMviEffect()
    public data object GoToMainMviEffect : TemperatureNavigationMviEffect()
    public data class GoToSettingsMviEffect(val device: DeviceEntity) : TemperatureNavigationMviEffect()
}

public sealed class TemperatureUiMviEffect : TemperatureMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : TemperatureUiMviEffect()
}