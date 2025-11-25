package dev.yamh.io.presentation.feature.splash.splash

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashMviEffect
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashNavigationMviEffect
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashUiMviEffect


public class SplashEffectHandler(
    navController: NavHostController
) : EffectHandler<SplashMviEffect, SplashNavigationMviEffect, SplashUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: SplashMviEffect) {
        when (effect) {
            is SplashUiMviEffect -> proceedUiEffect(effect)
            is SplashNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: SplashNavigationMviEffect) {
        when (effect) {
            SplashNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            SplashNavigationMviEffect.GoToAuthMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Authorization, cleanBackStack = true
            )

            SplashNavigationMviEffect.GoToOnboardingMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Onboarding, cleanBackStack = true
            )

            SplashNavigationMviEffect.GoToHomeMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Homes, cleanBackStack = true
            )
        }
    }

}
