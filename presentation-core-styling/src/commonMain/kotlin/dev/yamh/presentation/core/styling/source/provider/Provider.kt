package dev.yamh.presentation.core.styling.source.provider

import androidx.compose.runtime.staticCompositionLocalOf
import dev.yamh.presentation.core.styling.core.ThemeColor
import dev.yamh.presentation.core.styling.core.ThemeFontSize
import dev.yamh.presentation.core.styling.core.ThemeLineHeight
import dev.yamh.presentation.core.styling.core.ThemeSize
import dev.yamh.presentation.core.styling.core.ThemeSpacing
import dev.yamh.presentation.core.styling.core.ThemeTypography

internal val LocalThemeColor =
    staticCompositionLocalOf<ThemeColor> {
        error("No implementation")
    }
internal val LocalThemeFontSize =
    staticCompositionLocalOf<ThemeFontSize> {
        error("No implementation")
    }
internal val LocalThemeSize =
    staticCompositionLocalOf<ThemeSize> {
        error("No implementation")
    }
internal val LocalThemeLineHeight =
    staticCompositionLocalOf<ThemeLineHeight> {
        error("No implementation")
    }
internal val LocalThemeSpacing =
    staticCompositionLocalOf<ThemeSpacing> {
        error("No implementation")
    }
internal val LocalThemeTypography =
    staticCompositionLocalOf<ThemeTypography> {
        error("No implementation")
    }
