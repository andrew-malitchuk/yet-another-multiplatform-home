package dev.yamh.io.presentation.feature.device.source.switcher

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.device.navigation.DeviceInnerNavigationDirection
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherMviEffect
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherUiMviEffect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class SwitcherEffectHandler(
    navController: NavHostController
) : EffectHandler<SwitcherMviEffect, SwitcherNavigationMviEffect, SwitcherUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: SwitcherMviEffect) {
        when (effect) {
            is SwitcherUiMviEffect -> proceedUiEffect(effect)
            is SwitcherNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: SwitcherNavigationMviEffect) {
        when (effect) {
            SwitcherNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            SwitcherNavigationMviEffect.OnNavBackClickMviEffect ->
                 navController.popBackStack()

            is SwitcherNavigationMviEffect.GoToSettingsMviEffect ->  navController.safeNavigation(
                route = DeviceInnerNavigationDirection.Settings(device = Json.encodeToString(effect.device))
            )
        }
    }

    override fun proceedUiEffect(effect: SwitcherUiMviEffect) {
        when (effect) {
            is SwitcherUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
