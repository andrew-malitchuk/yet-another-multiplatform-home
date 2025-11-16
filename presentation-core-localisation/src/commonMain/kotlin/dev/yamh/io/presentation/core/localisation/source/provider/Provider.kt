package dev.yamh.io.presentation.core.localisation.source.provider

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import dev.yamh.io.presentation.core.localisation.source.Localisation

public val LocalLocalisation: ProvidableCompositionLocal<Localisation> =
    staticCompositionLocalOf {
        error("No implementation")
    }