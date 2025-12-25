package dev.yamh.io.presentation.feature.onboarding.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class OnboardingInnerNavigationDirection {
    @Serializable
    public data object Onboarding : OnboardingInnerNavigationDirection()
}