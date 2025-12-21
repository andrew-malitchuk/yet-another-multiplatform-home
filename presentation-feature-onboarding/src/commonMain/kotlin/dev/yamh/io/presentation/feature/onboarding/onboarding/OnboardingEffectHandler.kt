package dev.yamh.io.presentation.feature.onboarding.onboarding

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingMviEffect
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingNavigationMviEffect
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingUiMviEffect

public class OnboardingEffectHandler(
    navController: NavHostController
) : EffectHandler<OnboardingMviEffect, OnboardingNavigationMviEffect, OnboardingUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: OnboardingMviEffect) {
        when (effect) {
            is OnboardingUiMviEffect -> proceedUiEffect(effect)
            is OnboardingNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: OnboardingNavigationMviEffect) {
        when (effect) {
            OnboardingNavigationMviEffect.GoToAuthorizationMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Authorization, cleanBackStack = true
            )

            OnboardingNavigationMviEffect.OnNavBackClickMviEffect -> {
                navController.popBackStack()
            }
        }
    }

    override fun proceedUiEffect(effect: OnboardingUiMviEffect) {
        when (effect) {
            is OnboardingUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
