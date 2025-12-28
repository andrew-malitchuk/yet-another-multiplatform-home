package dev.yamh.io.presentation.feature.room.room

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.room.room.mvi.RoomMviEffect
import dev.yamh.io.presentation.feature.room.room.mvi.RoomNavigationMviEffect
import dev.yamh.io.presentation.feature.room.room.mvi.RoomUiMviEffect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class RoomEffectHandler(
    navController: NavHostController
) : EffectHandler<RoomMviEffect, RoomNavigationMviEffect, RoomUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: RoomMviEffect) {
        when (effect) {
            is RoomUiMviEffect -> proceedUiEffect(effect)
            is RoomNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: RoomNavigationMviEffect) {
        when (effect) {
            RoomNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            RoomNavigationMviEffect.OnNavBackClickMviEffect ->
                navController.popBackStack()

            is RoomNavigationMviEffect.GoToDeviceEffect -> {
                navController.safeNavigation(
                    route = GlobalNavigation.Device(
                        Json.encodeToString(effect.device)
                    )
                )
            }
        }
    }

    override fun proceedUiEffect(effect: RoomUiMviEffect) {
        when (effect) {
            is RoomUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
