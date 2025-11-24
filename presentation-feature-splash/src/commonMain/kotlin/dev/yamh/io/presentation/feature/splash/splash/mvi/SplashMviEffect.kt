package dev.yamh.io.presentation.feature.splash.splash.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect


public sealed interface SplashMviEffect : MviEffect

public sealed class SplashNavigationMviEffect : SplashMviEffect, MviNavigationEffect {
    public data object GoToMainMviEffect : SplashNavigationMviEffect()
    public data object GoToAuthMviEffect : SplashNavigationMviEffect()
    public data object GoToHomeMviEffect : SplashNavigationMviEffect()
    public data object GoToOnboardingMviEffect : SplashNavigationMviEffect()
}

public sealed class SplashUiMviEffect : SplashMviEffect, MviUiEffect