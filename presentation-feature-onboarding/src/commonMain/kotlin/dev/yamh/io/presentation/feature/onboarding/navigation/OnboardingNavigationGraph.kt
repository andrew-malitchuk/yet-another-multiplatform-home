package dev.yamh.io.presentation.feature.onboarding.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.onboarding.onboarding.OnboardingScreen

context(NavHostController)
public fun NavGraphBuilder.onboardingNavigationGraph():Unit = with(this@NavHostController) {
    navigation<GlobalNavigation.Onboarding>(
        startDestination = OnboardingInnerNavigationDirection.Onboarding,
    ) {
        composable<OnboardingInnerNavigationDirection.Onboarding> {
            OnboardingScreen()
        }
    }
}
