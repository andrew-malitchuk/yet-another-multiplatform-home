package dev.yamh.io.presentation.feature.device.source.switcher.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class SwitcherState(
    val isLoading: Boolean = false,
    val device: DeviceEntity? = null,
    val onOff: Boolean = false,
    val level: Float = 255f
) : MviState