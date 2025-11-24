package dev.yamh.io.presentation.feature.splash.splash.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class SplashIntent : MviIntent {
    public data object Setup : SplashIntent()
    public data object GoToMainIntent : SplashIntent()
    public data object GoToAuthIntent : SplashIntent()
    public data object GoToOnboardingIntent : SplashIntent()
}