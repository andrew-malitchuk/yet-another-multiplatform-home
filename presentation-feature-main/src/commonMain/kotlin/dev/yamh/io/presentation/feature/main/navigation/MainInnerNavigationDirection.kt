package dev.yamh.io.presentation.feature.main.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class MainInnerNavigationDirection {
    @Serializable
    public data object Main : MainInnerNavigationDirection()
}