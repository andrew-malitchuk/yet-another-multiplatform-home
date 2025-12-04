package dev.yamh.io.presentation.feature.authorization.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class AuthorizationInnerNavigationDirection {
    @Serializable
    public data object Authorization : AuthorizationInnerNavigationDirection()
}