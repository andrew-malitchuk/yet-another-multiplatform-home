package dev.yamh.io.presentation.feature.device.source.window

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.device.navigation.DeviceInnerNavigationDirection
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowMviEffect
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowUiMviEffect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class WindowEffectHandler(
    navController: NavHostController
) : EffectHandler<WindowMviEffect, WindowNavigationMviEffect, WindowUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: WindowMviEffect) {
        when (effect) {
            is WindowUiMviEffect -> proceedUiEffect(effect)
            is WindowNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: WindowNavigationMviEffect) {
        when (effect) {
            WindowNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            WindowNavigationMviEffect.OnNavBackClickMviEffect ->
                 navController.popBackStack()

            is WindowNavigationMviEffect.GoToSettingsMviEffect ->  navController.safeNavigation(
                route = DeviceInnerNavigationDirection.Settings(device = Json.encodeToString(effect.device))
            )
        }
    }

    override fun proceedUiEffect(effect: WindowUiMviEffect) {
        when (effect) {
            is WindowUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
