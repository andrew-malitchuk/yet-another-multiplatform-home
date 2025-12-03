package dev.yamh.io.presentation.feature.device.source.switcher.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface SwitcherMviEffect : MviEffect

public sealed class SwitcherNavigationMviEffect : SwitcherMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : SwitcherNavigationMviEffect()
    public data object GoToMainMviEffect : SwitcherNavigationMviEffect()
    public data class GoToSettingsMviEffect(val device: DeviceEntity) :
        SwitcherNavigationMviEffect()
}

public sealed class SwitcherUiMviEffect : SwitcherMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : SwitcherUiMviEffect()
}