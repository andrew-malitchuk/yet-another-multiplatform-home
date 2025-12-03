package dev.yamh.io.presentation.feature.device.source.contact.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface ContactMviEffect : MviEffect

public sealed class ContactNavigationMviEffect : ContactMviEffect, MviNavigationEffect {
    public data object OnNavBackClickMviEffect : ContactNavigationMviEffect()
    public data object GoToMainMviEffect : ContactNavigationMviEffect()
    public data class GoToSettingsMviEffect(val device: DeviceEntity) : ContactNavigationMviEffect()
}

public sealed class ContactUiMviEffect : ContactMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : ContactUiMviEffect()
}