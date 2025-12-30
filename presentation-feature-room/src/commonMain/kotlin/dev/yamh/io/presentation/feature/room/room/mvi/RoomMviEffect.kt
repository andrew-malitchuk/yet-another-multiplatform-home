package dev.yamh.io.presentation.feature.room.room.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface RoomMviEffect : MviEffect

public sealed class RoomNavigationMviEffect : RoomMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : RoomNavigationMviEffect()
    public data object GoToMainMviEffect : RoomNavigationMviEffect()
    public data class GoToDeviceEffect(val device: DeviceEntity) : RoomNavigationMviEffect()
}

public sealed class RoomUiMviEffect : RoomMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : RoomUiMviEffect()
}