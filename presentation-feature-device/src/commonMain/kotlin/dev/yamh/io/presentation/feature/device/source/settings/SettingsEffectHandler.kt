package dev.yamh.io.presentation.feature.device.source.settings

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsMviEffect
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsUiMviEffect

public class SettingsEffectHandler(
    navController: NavHostController
) : EffectHandler<SettingsMviEffect, SettingsNavigationMviEffect, SettingsUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: SettingsMviEffect) {
        when (effect) {
            is SettingsUiMviEffect -> proceedUiEffect(effect)
            is SettingsNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: SettingsNavigationMviEffect) {
        when (effect) {
            SettingsNavigationMviEffect.GoToAuthorizationMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Authorization, cleanBackStack = true
            )

            SettingsNavigationMviEffect.OnNavBackClickMviEffect ->
                navController.popBackStack()

        }
    }

    override fun proceedUiEffect(effect: SettingsUiMviEffect) {
        when (effect) {
            is SettingsUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
