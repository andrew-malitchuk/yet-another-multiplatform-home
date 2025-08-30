package dev.yamh.io.presentation.core.ui.source.kit.atom.button.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
public interface ButtonSizes {
    public val iconSize: Dp
    public val borderSize: Dp
    public val contentPadding: PaddingValues
    public val minWidth: Dp
    public val minHeight: Dp
}

public data class ButtonSizeConfigure(
    val iconSize: Dp,
    val borderSize: Dp,
    val contentPadding: PaddingValues,
    val minWidth: Dp,
    val minHeight: Dp
) {
    public fun getButtonSize(): ButtonSizes {
        return object : ButtonSizes {
            override val iconSize: Dp = this@ButtonSizeConfigure.iconSize
            override val borderSize: Dp = this@ButtonSizeConfigure.borderSize
            override val contentPadding: PaddingValues = this@ButtonSizeConfigure.contentPadding
            override val minWidth: Dp = this@ButtonSizeConfigure.minWidth
            override val minHeight: Dp = this@ButtonSizeConfigure.minHeight
        }
    }
}