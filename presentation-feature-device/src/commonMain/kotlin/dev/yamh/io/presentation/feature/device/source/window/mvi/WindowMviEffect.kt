package dev.yamh.io.presentation.feature.device.source.window.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface WindowMviEffect : MviEffect

public sealed class WindowNavigationMviEffect : WindowMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : WindowNavigationMviEffect()
    public data object GoToMainMviEffect : WindowNavigationMviEffect()
    public data class GoToSettingsMviEffect(val device: DeviceEntity) :
        WindowNavigationMviEffect()
}

public sealed class WindowUiMviEffect : WindowMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : WindowUiMviEffect()
}