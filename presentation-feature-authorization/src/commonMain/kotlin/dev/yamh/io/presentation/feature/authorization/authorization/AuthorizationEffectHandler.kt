package dev.yamh.io.presentation.feature.authorization.authorization

import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.navigation.core.ext.safeNavigation
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.EffectHandler
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationMviEffect
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationNavigationMviEffect
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationUiMviEffect

public class AuthorizationEffectHandler(navController: NavHostController) :
    EffectHandler<AuthorizationMviEffect, AuthorizationNavigationMviEffect, AuthorizationUiMviEffect>(
        navController
    ) {

    public override fun proceedEffect(effect: AuthorizationMviEffect): Unit =
        with(navController) {
            when (effect) {
                is AuthorizationUiMviEffect -> Unit
                is AuthorizationNavigationMviEffect -> proceedNavigationEffect(effect)
            }
        }


    override fun proceedNavigationEffect(effect: AuthorizationNavigationMviEffect) {
        when (effect) {
            AuthorizationNavigationMviEffect.GoToHomesMviEffect -> navController.safeNavigation(
                GlobalNavigation.Homes
            )
        }
    }

}