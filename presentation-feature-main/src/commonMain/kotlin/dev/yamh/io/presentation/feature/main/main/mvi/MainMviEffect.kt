package dev.yamh.io.presentation.feature.main.main.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface MainMviEffect : MviEffect

public sealed class MainNavigationMviEffect : MainMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : MainNavigationMviEffect()
    public data object GoToAuthorizationMviEffect : MainNavigationMviEffect()
    public data class GoToRoomMviEffect(val room: RoomEntity) : MainNavigationMviEffect()
    public data object GoToSettingsEffect : MainNavigationMviEffect()
    public data object GoToHomeEffect : MainNavigationMviEffect()
    public data class GoToDeviceEffect(val device: DeviceEntity) : MainNavigationMviEffect()

}

public sealed class MainUiMviEffect : MainMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : MainUiMviEffect()
}