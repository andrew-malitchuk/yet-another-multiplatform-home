package dev.yamh.io.presentation.feature.main.main

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation.*
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.main.main.mvi.MainMviEffect
import dev.yamh.io.presentation.feature.main.main.mvi.MainNavigationMviEffect
import dev.yamh.io.presentation.feature.main.main.mvi.MainUiMviEffect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class MainEffectHandler(
    navController: NavHostController
) : EffectHandler<MainMviEffect, MainNavigationMviEffect, MainUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: MainMviEffect) {
        when (effect) {
            is MainUiMviEffect -> proceedUiEffect(effect)
            is MainNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: MainNavigationMviEffect) {

        when (effect) {
            MainNavigationMviEffect.GoToAuthorizationMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Authorization, cleanBackStack = true
            )

            MainNavigationMviEffect.OnNavBackClickMviEffect -> {
                 navController.popBackStack()
            }

            is MainNavigationMviEffect.GoToRoomMviEffect -> navController.safeNavigation(
                route = Room(Json.encodeToString(effect.room))
            )

            MainNavigationMviEffect.GoToSettingsEffect -> navController.safeNavigation(
                route = GlobalNavigation.Settings
            )

            MainNavigationMviEffect.GoToHomeEffect -> navController.safeNavigation(
                route = GlobalNavigation.Homes
            )

            is MainNavigationMviEffect.GoToDeviceEffect -> {
                navController.safeNavigation(
                    route = GlobalNavigation.Device(
                        Json.encodeToString(effect.device)
                    )
                )
            }
        }
    }

    override fun proceedUiEffect(effect: MainUiMviEffect) {
        when (effect) {
            is MainUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
