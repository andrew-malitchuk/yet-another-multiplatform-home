package dev.yamh.io.presentation.feature.onboarding.onboarding.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface OnboardingMviEffect : MviEffect

public sealed class OnboardingNavigationMviEffect : OnboardingMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : OnboardingNavigationMviEffect()
    public data object GoToAuthorizationMviEffect : OnboardingNavigationMviEffect()
}

public sealed class OnboardingUiMviEffect : OnboardingMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : OnboardingUiMviEffect()
}