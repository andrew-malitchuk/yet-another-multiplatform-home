package dev.yamh.io.presentation.feature.device.source.settings.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class SettingsState(
    val isLoading: Boolean = false,
    val content: String = "Hello Settings32",
    val isDeviceSelected: Boolean = false,
    val device: DeviceEntity? = null,
) : MviState