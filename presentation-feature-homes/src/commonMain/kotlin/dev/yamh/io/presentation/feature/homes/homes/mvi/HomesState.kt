package dev.yamh.io.presentation.feature.homes.homes.mvi

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class HomesState(
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val isError: Boolean = false,
    val content: List<HomeEntity>? = null,
    val selectedHome: HomeEntity? = null,
) : MviState