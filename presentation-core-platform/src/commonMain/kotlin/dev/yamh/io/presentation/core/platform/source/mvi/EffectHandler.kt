package dev.yamh.io.presentation.core.platform.source.mvi

import androidx.navigation.NavHostController

public open class EffectHandler<MviEffect, MviNavigationEffect, MviUiEffect>(
    protected open val navController: NavHostController
) {
    public open fun proceedEffect(effect: MviEffect) {}
    public open fun proceedNavigationEffect(effect: MviNavigationEffect) {}
    public open fun proceedUiEffect(effect: MviUiEffect) {}
}