package dev.yamh.io.presentation.feature.device.source.contact

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.device.navigation.DeviceInnerNavigationDirection
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactMviEffect
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactUiMviEffect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class ContactEffectHandler(
    navController: NavHostController
) : EffectHandler<ContactMviEffect, ContactNavigationMviEffect, ContactUiMviEffect>(
    navController
) {

    override fun proceedEffect(effect: ContactMviEffect) {
        when (effect) {
            is ContactUiMviEffect -> proceedUiEffect(effect)
            is ContactNavigationMviEffect -> proceedNavigationEffect(effect)
        }
    }

    override fun proceedNavigationEffect(effect: ContactNavigationMviEffect) {
        when (effect) {
            ContactNavigationMviEffect.GoToMainMviEffect -> navController.safeNavigation(
                route = GlobalNavigation.Main, cleanBackStack = true
            )

            ContactNavigationMviEffect.OnNavBackClickMviEffect ->
                 navController.popBackStack()

            is ContactNavigationMviEffect.GoToSettingsMviEffect -> navController.safeNavigation(
                route = DeviceInnerNavigationDirection.Settings(device = Json.encodeToString(effect.device))
            )
        }
    }

    override fun proceedUiEffect(effect: ContactUiMviEffect) {
        when (effect) {
            is ContactUiMviEffect.ShowErrorMessageMviEffect -> Unit
        }
    }
}
