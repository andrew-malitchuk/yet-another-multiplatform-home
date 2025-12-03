package dev.yamh.io.presentation.feature.device.source.temperature

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.device.navigation.DeviceInnerNavigationDirection
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureMviEffect
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureUiMviEffect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class TemperatureEffectHandler(
    navController: NavHostController
) : EffectHandler<TemperatureMviEffect, TemperatureNavigationMviEffect, TemperatureUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: TemperatureMviEffect) {
        when (effect) {
            is TemperatureUiMviEffect -> proceedUiEffect(effect)
            is TemperatureNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: TemperatureNavigationMviEffect) {
        when (effect) {
            TemperatureNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            TemperatureNavigationMviEffect.OnNavBackClickMviEffect ->
                 navController.popBackStack()

           is TemperatureNavigationMviEffect.GoToSettingsMviEffect -> navController.safeNavigation(
                route = DeviceInnerNavigationDirection.Settings(device = Json.encodeToString(effect.device))
            )
        }
    }

    override fun proceedUiEffect(effect: TemperatureUiMviEffect) {
        when (effect) {
            is TemperatureUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
