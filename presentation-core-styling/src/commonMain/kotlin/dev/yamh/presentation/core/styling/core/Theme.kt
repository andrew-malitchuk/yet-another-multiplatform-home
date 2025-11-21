package dev.yamh.presentation.core.styling.core

import androidx.compose.runtime.Composable
import dev.yamh.presentation.core.styling.source.provider.LocalThemeColor
import dev.yamh.presentation.core.styling.source.provider.LocalThemeFontSize
import dev.yamh.presentation.core.styling.source.provider.LocalThemeLineHeight
import dev.yamh.presentation.core.styling.source.provider.LocalThemeSize
import dev.yamh.presentation.core.styling.source.provider.LocalThemeSpacing
import dev.yamh.presentation.core.styling.source.provider.LocalThemeTypography

public object Theme {
    public val color: ThemeColor
        @Composable
        get() = LocalThemeColor.current
    public val fontSize: ThemeFontSize
        @Composable
        get() = LocalThemeFontSize.current
    public val size: ThemeSize
        @Composable
        get() = LocalThemeSize.current
    public  val lineHeight: ThemeLineHeight
        @Composable
        get() = LocalThemeLineHeight.current
    public  val spacing: ThemeSpacing
        @Composable
        get() = LocalThemeSpacing.current
    public  val typography: ThemeTypography
        @Composable
        get() = LocalThemeTypography.current
}