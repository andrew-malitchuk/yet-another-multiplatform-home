package dev.yamh.io.presentation.feature.settings.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.settings.settings.SettingsScreen

context(NavHostController)
public fun NavGraphBuilder.settingsNavigationGraph() {
    navigation<GlobalNavigation.Settings>(
        startDestination = SettingsInnerNavigationDirection.Settings,
    ) {
        composable<SettingsInnerNavigationDirection.Settings>(
            enterTransition = {
                scaleIn(initialScale = 0.92f, animationSpec = tween(350)) + fadeIn(
                    tween(350)
                )
            },
            exitTransition = { fadeOut(tween(200)) },
            popEnterTransition = {
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(350)
                ) + fadeIn(tween(350))
            },
            popExitTransition = { fadeOut(tween(200)) }
        ) {
            SettingsScreen()
        }
    }
}
