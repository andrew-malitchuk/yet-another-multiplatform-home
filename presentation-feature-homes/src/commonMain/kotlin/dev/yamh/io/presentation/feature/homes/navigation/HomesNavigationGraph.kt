package dev.yamh.io.presentation.feature.homes.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.homes.homes.HomesScreen

context(NavHostController)
public fun NavGraphBuilder.homesNavigationGraph():Unit = with(this@NavHostController) {
    navigation<GlobalNavigation.Homes>(
        startDestination = HomesInnerNavigationDirection.Homes,
    ) {
        composable<HomesInnerNavigationDirection.Homes> {
            HomesScreen()
        }
    }
}
