package dev.yamh.io.presentation.feature.settings.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class SettingsInnerNavigationDirection {
    @Serializable
    public data object Settings : SettingsInnerNavigationDirection()
}