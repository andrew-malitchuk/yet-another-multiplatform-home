package dev.yamh.io.presentation.feature.homes.homes

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesMviEffect
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesNavigationMviEffect
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesUiMviEffect

public class HomesEffectHandler(
    navController: NavHostController
) : EffectHandler<HomesMviEffect, HomesNavigationMviEffect, HomesUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: HomesMviEffect) {
        when (effect) {
            is HomesUiMviEffect -> proceedUiEffect(effect)
            is HomesNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: HomesNavigationMviEffect) {
        when (effect) {
            HomesNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            HomesNavigationMviEffect.GoToSettingsEffect -> navController.safeNavigation(
                route = GlobalNavigation.Settings
            )
        }
    }

    override fun proceedUiEffect(effect: HomesUiMviEffect) {
        when (effect) {
            is HomesUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
