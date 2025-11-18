package dev.yamh.io.presentation.core.navigation.source.navigation

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import dev.yamh.domain.core.source.model.room.RoomEntity
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

@Serializable
public sealed class GlobalNavigation {
    @Serializable
    public data object Splash : GlobalNavigation()

    @Serializable
    public data object Onboarding : GlobalNavigation()

    @Serializable
    public data object Authorization : GlobalNavigation()

    @Serializable
    public data object Homes : GlobalNavigation()

    @Serializable
    public data object Main : GlobalNavigation()

    @Serializable
    public data class Room(val room: String) : GlobalNavigation()

    @Serializable
    public data class Device(val device: String) : GlobalNavigation()

    @Serializable
    public data object Settings : GlobalNavigation()
}

