package dev.yamh.io.presentation.feature.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.splash.splash.SplashScreen

context(NavHostController)
public fun NavGraphBuilder.splashNavigationGraph() {
    navigation<GlobalNavigation.Splash>(
        startDestination = SplashInnerNavigationDirection.Splash,
    ) {
        composable<SplashInnerNavigationDirection.Splash> {
            SplashScreen()
        }
    }
}
