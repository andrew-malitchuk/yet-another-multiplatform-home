package dev.yamh.io.presentation.feature.authorization.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.authorization.authorization.AuthorizationScreen

context(NavHostController)
public fun NavGraphBuilder.authorizationNavigationGraph() {
    navigation<GlobalNavigation.Authorization>(
        startDestination = AuthorizationInnerNavigationDirection.Authorization,
    ) {
        composable<AuthorizationInnerNavigationDirection.Authorization> {
            AuthorizationScreen()
        }
    }
}
