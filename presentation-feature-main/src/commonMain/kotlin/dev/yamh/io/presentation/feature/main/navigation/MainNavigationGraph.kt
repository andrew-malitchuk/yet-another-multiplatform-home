package dev.yamh.io.presentation.feature.main.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.main.main.MainScreen

context(NavHostController)
public fun NavGraphBuilder.mainNavigationGraph(): Unit = with(this@NavHostController) {
    navigation<GlobalNavigation.Main>(
        startDestination = MainInnerNavigationDirection.Main,
    ) {
        composable<MainInnerNavigationDirection.Main>(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(),
                    targetAlpha = 0.99f
                )
            },
            popEnterTransition = {
                fadeIn()
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }
                )
            },
        ) {
            MainScreen()
        }
    }
}
