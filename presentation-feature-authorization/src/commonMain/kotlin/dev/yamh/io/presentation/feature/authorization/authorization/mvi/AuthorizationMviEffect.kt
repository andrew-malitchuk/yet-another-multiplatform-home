package dev.yamh.io.presentation.feature.authorization.authorization.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface AuthorizationMviEffect : MviEffect

public sealed class AuthorizationNavigationMviEffect : AuthorizationMviEffect, MviNavigationEffect {
    public data object GoToHomesMviEffect : AuthorizationNavigationMviEffect()
}

public sealed class AuthorizationUiMviEffect : AuthorizationMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : AuthorizationUiMviEffect()
}