package dev.yamh.io.presentation.feature.onboarding.onboarding.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class OnboardingIntent : MviIntent {
    public data object Setup : OnboardingIntent()
    public data object OnBackClickIntent : OnboardingIntent()
    public data object OnOnboardingDoneIntent : OnboardingIntent()

}