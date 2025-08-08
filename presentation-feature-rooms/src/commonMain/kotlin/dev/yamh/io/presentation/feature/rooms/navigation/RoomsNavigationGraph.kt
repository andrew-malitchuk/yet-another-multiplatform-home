package dev.yamh.io.presentation.feature.rooms.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yamh.io.presentation.core.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.rooms.rooms.RoomsScreen

context(NavHostController)
public fun NavGraphBuilder.roomsNavigationGraph() {
    navigation<GlobalNavigation.Rooms>(
        startDestination = RoomsInnerNavigationDirection.Rooms,
    ) {
        composable<RoomsInnerNavigationDirection.Rooms> {
            RoomsScreen()
        }
    }
}
