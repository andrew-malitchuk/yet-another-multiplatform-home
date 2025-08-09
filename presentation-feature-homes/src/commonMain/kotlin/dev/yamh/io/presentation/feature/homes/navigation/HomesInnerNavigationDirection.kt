package dev.yamh.io.presentation.feature.homes.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class HomesInnerNavigationDirection {
    @Serializable
    public data object Homes : HomesInnerNavigationDirection()
}