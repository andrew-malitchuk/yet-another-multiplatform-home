package dev.yamh.io.presentation.feature.device.source.contact.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class ContactIntent : MviIntent {
    public data class Setup(val device: DeviceEntity) : ContactIntent()
    public data object OnBackClickIntent : ContactIntent()
    public data object GoToMainIntent : ContactIntent()
    public data class GoToSettingsIntent(val device: DeviceEntity) : ContactIntent()
}