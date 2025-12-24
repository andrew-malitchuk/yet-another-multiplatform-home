package dev.yamh.io.presentation.feature.onboarding.onboarding.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class OnboardingState(
    val isLoading: Boolean = false,
) : MviState