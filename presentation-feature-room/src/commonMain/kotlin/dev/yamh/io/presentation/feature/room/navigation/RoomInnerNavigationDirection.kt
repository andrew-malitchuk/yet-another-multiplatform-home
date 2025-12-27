package dev.yamh.io.presentation.feature.room.navigation

import dev.yamh.domain.core.source.model.room.RoomEntity
import kotlinx.serialization.Serializable

@Serializable
public sealed class RoomInnerNavigationDirection {
    @Serializable
    public data class Room(val room: String? = null) : RoomInnerNavigationDirection()
}