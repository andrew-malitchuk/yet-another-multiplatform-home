package dev.yamh.io.presentation.feature.main.main.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class MainIntent : MviIntent {
    public data object Setup : MainIntent()
    public data object RefreshIntent : MainIntent()
    public data object OnMainDoneIntent : MainIntent()
    public data class GoToRoomIntent(val roomEntity: RoomEntity) : MainIntent()
    public data object GoToHomeIntent : MainIntent()
    public data object GoToSettingsIntent : MainIntent()
    public data class GoToDeviceIntent(val device: DeviceEntity) : MainIntent()
}