package dev.yamh.io.presentation.feature.splash.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class SplashInnerNavigationDirection {
    @Serializable
    public data object Splash : SplashInnerNavigationDirection()
}