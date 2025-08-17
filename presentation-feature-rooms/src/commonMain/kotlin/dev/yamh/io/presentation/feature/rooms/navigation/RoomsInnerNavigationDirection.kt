package dev.yamh.io.presentation.feature.rooms.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class RoomsInnerNavigationDirection {
    @Serializable
    public data object Rooms : RoomsInnerNavigationDirection()
}