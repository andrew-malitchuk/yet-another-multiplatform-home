package dev.yamh.io.presentation.feature.onboarding.onboarding

import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingIntent
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingMviEffect
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingNavigationMviEffect
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class OnboardingViewModel :
    BaseViewModel<OnboardingState, OnboardingIntent, OnboardingMviEffect>() {

    override val state: StateFlow<OnboardingState> = MutableStateFlow(OnboardingState())

    override val intent: StateFlow<OnboardingIntent?> = MutableStateFlow(null)

    override val effect: Channel<OnboardingMviEffect> = Channel(capacity = Channel.CONFLATED)

    override fun onIntent(intent: OnboardingIntent) {
        when (intent) {
            OnboardingIntent.OnBackClickIntent -> onEffect(OnboardingNavigationMviEffect.OnNavBackClickMviEffect)
            OnboardingIntent.Setup -> Unit
            OnboardingIntent.OnOnboardingDoneIntent -> onEffect(OnboardingNavigationMviEffect.GoToAuthorizationMviEffect)
        }
    }

    override fun onEffect(effect: OnboardingMviEffect) {
        executeCoroutine {
            this@OnboardingViewModel.effect.send(effect)
        }
    }
}


