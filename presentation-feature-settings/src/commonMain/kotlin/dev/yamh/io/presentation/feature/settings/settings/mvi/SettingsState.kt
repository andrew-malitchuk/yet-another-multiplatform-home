package dev.yamh.io.presentation.feature.settings.settings.mvi

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class SettingsState(
    val isLoading: Boolean = false,
    val language: LanguageEntity? = null,
    val theme: ThemeEntity? = null,
) : MviState