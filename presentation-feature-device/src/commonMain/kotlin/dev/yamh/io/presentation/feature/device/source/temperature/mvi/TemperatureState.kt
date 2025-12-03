package dev.yamh.io.presentation.feature.device.source.temperature.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class TemperatureState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val device: DeviceEntity? = null,
    val temperature: String? = null
) : MviState