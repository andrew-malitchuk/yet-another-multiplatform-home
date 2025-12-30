package dev.yamh.io.presentation.feature.room.room.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class RoomIntent : MviIntent {
    public data class Setup(val room: RoomEntity) : RoomIntent()
    public data object OnBackClickIntent : RoomIntent()
    public data object GoToMainIntent : RoomIntent()
    public data class GoToDeviceIntent(val device: DeviceEntity) : RoomIntent()
    public data class SearchIntent(val query: String) : RoomIntent()
    public data object RefreshIntent : RoomIntent()
}