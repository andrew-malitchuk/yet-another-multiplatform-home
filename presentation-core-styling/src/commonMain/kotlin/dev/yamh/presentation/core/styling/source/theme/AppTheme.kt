package dev.yamh.presentation.core.styling.source.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import dev.yamh.io.presentation.core.localisation.core.configure.Language
import dev.yamh.io.presentation.core.localisation.core.configure.getLocalisation
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.presentation.core.styling.source.attribute.attributeFontSize
import dev.yamh.presentation.core.styling.source.attribute.attributeLineHeight
import dev.yamh.presentation.core.styling.source.attribute.attributeSize
import dev.yamh.presentation.core.styling.source.attribute.attributeSpacing
import dev.yamh.presentation.core.styling.source.attribute.color.attributeDarkColorPalette
import dev.yamh.presentation.core.styling.source.attribute.color.attributeLightColorPalette
import dev.yamh.presentation.core.styling.source.provider.LocalThemeColor
import dev.yamh.presentation.core.styling.source.provider.LocalThemeFontSize
import dev.yamh.presentation.core.styling.source.provider.LocalThemeLineHeight
import dev.yamh.presentation.core.styling.source.provider.LocalThemeSize
import dev.yamh.presentation.core.styling.source.provider.LocalThemeSpacing
import dev.yamh.presentation.core.styling.source.provider.LocalThemeTypography
import dev.yamh.presentation.core.styling.source.attribute.AttributeTypography

@Composable
public fun AppTheme(
    useDarkTheme: Boolean = false,
    language: String,
    content: @Composable () -> Unit,
) {
    val currentColorPalette = when {
        useDarkTheme -> attributeDarkColorPalette
        else -> attributeLightColorPalette
    }
    val currentLang =
        getLocalisation(Language.entries.firstOrNull { it.iso == language } ?: Language.English)

    CompositionLocalProvider(
        LocalThemeColor provides currentColorPalette,
        LocalThemeFontSize provides attributeFontSize,
        LocalThemeSize provides attributeSize,
        LocalThemeLineHeight provides attributeLineHeight,
        LocalThemeSpacing provides attributeSpacing,
        LocalThemeTypography provides AttributeTypography(),
        LocalLocalisation provides currentLang,
        content = content,
    )
}