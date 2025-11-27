package dev.yamh.io.presentation.feature.settings.settings.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface SettingsMviEffect : MviEffect

public sealed class SettingsNavigationMviEffect : SettingsMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : SettingsNavigationMviEffect()
    public data object OnGoToGithubMviEffect : SettingsNavigationMviEffect()
    public data object OnGoToLinkedInMviEffect : SettingsNavigationMviEffect()
}

public sealed class SettingsUiMviEffect : SettingsMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : SettingsUiMviEffect()
}