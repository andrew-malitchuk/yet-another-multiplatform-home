package dev.yamh.io.presentation.feature.main.main.mvi

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class MainState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val selectedHome: HomeEntity? = null,
    val rooms: List<RoomEntity>? = null,
) : MviState