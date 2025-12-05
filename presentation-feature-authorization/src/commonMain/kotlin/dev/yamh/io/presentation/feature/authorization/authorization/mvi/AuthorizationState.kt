package dev.yamh.io.presentation.feature.authorization.authorization.mvi

import androidx.compose.runtime.State
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class AuthorizationState(
    val isLoading: Boolean = false,
    val error: String? = null,
) : MviState