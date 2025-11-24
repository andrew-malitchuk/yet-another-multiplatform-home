package dev.yamh.io.presentation.feature.splash.splash.mvi

import dev.yamh.domain.usecase.source.onboarding.IsOnboardingCompletedUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class SplashState(
    val isLoading: Boolean = false,
    val isOnboardingCompleted: Boolean?=null
) : MviState