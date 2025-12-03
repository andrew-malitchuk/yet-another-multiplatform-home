package dev.yamh.io.presentation.feature.device.source.window.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class WindowState(
    val isLoading: Boolean = false,
    val device: DeviceEntity? = null,
    val onOff: Boolean = false,
    val level: Float = 255f
) : MviState