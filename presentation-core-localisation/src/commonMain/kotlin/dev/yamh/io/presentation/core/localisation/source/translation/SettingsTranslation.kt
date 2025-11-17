package dev.yamh.io.presentation.core.localisation.source.translation

import dev.yamh.io.presentation.core.localisation.core.configure.Language
import dev.yamh.io.presentation.core.localisation.source.translation.base.BaseTranslation
import kotlinx.coroutines.internal.OpDescriptor

public data class SettingsTranslation(
    val biometry: String,
    val enabled: String,
    val language: String,
    val theme: String,
    val about: String,
    val aboutDescription: String,
    val information: String,
    val type: String,
    val customize: String,
    val selected: String,
) : BaseTranslation