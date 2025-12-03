package dev.yamh.io.presentation.feature.device.source.settings.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class SettingsIntent : MviIntent {
    public data class Setup(val device: DeviceEntity) : SettingsIntent()
    public data object OnBackClickIntent : SettingsIntent()
    public data object OnSettingsDoneIntent : SettingsIntent()
    public data class OnSettingsChangedIntent(val newState: Boolean) : SettingsIntent()
    public data class OnDeviceCustomTypeChangedIntent(val newType: DeviceCustomType) :
        SettingsIntent()
}