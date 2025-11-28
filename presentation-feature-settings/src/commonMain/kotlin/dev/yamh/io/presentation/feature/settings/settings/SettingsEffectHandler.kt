package dev.yamh.io.presentation.feature.settings.settings

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.core.util.Platform
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsMviEffect
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsNavigationMviEffect
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsUiMviEffect

public class SettingsEffectHandler(
    navController: NavHostController,
    private val platform: Platform,
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
            SettingsNavigationMviEffect.OnNavBackClickMviEffect ->
                navController.popBackStack()

            SettingsNavigationMviEffect.OnGoToGithubMviEffect -> platform.openUrl("https://github.com/andrew-malitchuk/yet-another-multiplatform-home")
            SettingsNavigationMviEffect.OnGoToLinkedInMviEffect -> platform.openUrl("https://www.linkedin.com/in/andrew-malitchuk")
        }
    }

    override fun proceedUiEffect(effect: SettingsUiMviEffect) {
        when (effect) {
            is SettingsUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
