package dev.yamh.io.presentation.feature.authorization.authorization.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class AuthorizationIntent : MviIntent {
    public data object Setup : AuthorizationIntent()
    public data object OnAuthorizeIntent : AuthorizationIntent()
    public data object OnAuthorizationDoneIntent : AuthorizationIntent()
}