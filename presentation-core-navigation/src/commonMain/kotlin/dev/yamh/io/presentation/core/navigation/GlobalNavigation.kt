package dev.yamh.io.presentation.core.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class GlobalNavigation {
    @Serializable
    public data object Homes : GlobalNavigation()
    @Serializable
    public data object Rooms : GlobalNavigation()
}
