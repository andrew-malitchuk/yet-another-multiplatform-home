package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp

@Immutable
public interface PushButtonSizes {
    public val iconSize: Dp
    public val borderSize: Dp
    public val contentPadding: PaddingValues
    public val minWidth: Dp
    public val minHeight: Dp
}

public data class PushButtonSizeConfigure(
    val iconSize: Dp,
    val borderSize: Dp,
    val contentPadding: PaddingValues,
    val minWidth: Dp,
    val minHeight: Dp
) {
    public fun getPushButtonSize(): PushButtonSizes {
        return object : PushButtonSizes {
            override val iconSize: Dp = this@PushButtonSizeConfigure.iconSize
            override val borderSize: Dp = this@PushButtonSizeConfigure.borderSize
            override val contentPadding: PaddingValues = this@PushButtonSizeConfigure.contentPadding
            override val minWidth: Dp = this@PushButtonSizeConfigure.minWidth
            override val minHeight: Dp = this@PushButtonSizeConfigure.minHeight

        }
    }
}