package dev.yamh.io.presentation.feature.room.navigation

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import androidx.navigation.toRoute
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.navigation.core.navtype.roomNavType
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.room.room.RoomScreen
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

context(NavHostController)
public fun NavGraphBuilder.roomNavigationGraph() {
    navigation<GlobalNavigation.Room>(
        startDestination = RoomInnerNavigationDirection.Room(),
    ) {
        composable<RoomInnerNavigationDirection.Room>(
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
        ) { backStackEntry ->
            val roomEntity =
                Json.decodeFromString<RoomEntity>(backStackEntry.toRoute<GlobalNavigation.Room>().room)
            RoomScreen(roomEntity)
        }
    }
}
