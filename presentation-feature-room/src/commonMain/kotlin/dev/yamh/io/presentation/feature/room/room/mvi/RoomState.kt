package dev.yamh.io.presentation.feature.room.room.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class RoomState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val room: RoomEntity? = null,
    val devices: List<DeviceEntity>? = null,
    val query: String? = null,
    val searchResult: List<DeviceEntity>? = null,
) : MviState