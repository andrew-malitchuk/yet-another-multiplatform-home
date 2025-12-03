package dev.yamh.io.presentation.feature.device.source.settings.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface SettingsMviEffect : MviEffect

public sealed class SettingsNavigationMviEffect : SettingsMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : SettingsNavigationMviEffect()
    public data object GoToAuthorizationMviEffect : SettingsNavigationMviEffect()
}

public sealed class SettingsUiMviEffect : SettingsMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : SettingsUiMviEffect()
}