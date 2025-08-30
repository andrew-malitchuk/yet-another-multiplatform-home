package dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary

import androidx.compose.animation.core.EaseInCirc
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonAnimation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonAnimationConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonColor
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonColorConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonColors
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonShapeConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonShapes
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonSizeConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonSizes
import dev.yamh.presentation.core.styling.core.Theme

public object TertiaryButtonDefaults {
    public val colors: ButtonColors
        @Composable
        get() = ButtonColorConfigure(
            foregroundColor = ButtonColor(
                disabledColor = Theme.color.primary0,
                focusedColor = Theme.color.secondary1,
                hoveredColor = Theme.color.secondary1,
                pressedColor = Theme.color.secondary1,
                defaultColor = Theme.color.primary0
            ),
            backgroundColor = ButtonColor(
                disabledColor = Theme.color.primary1,
                focusedColor = Theme.color.primary2,
                hoveredColor = Theme.color.primary2,
                pressedColor = Theme.color.accent0,
                defaultColor = Theme.color.primary1,
            ),
            borderColor = ButtonColor(
                disabledColor = Color.Transparent,
                focusedColor = Color.Transparent,
                hoveredColor = Color.Transparent,
                pressedColor = Color.Transparent,
                defaultColor = Color.Transparent
            )
        ).getButtonColors()

    public val sizes: ButtonSizes = ButtonSizeConfigure(
        iconSize = 24.dp,
        borderSize = 0.dp,
        contentPadding = PaddingValues(8.dp),
        minWidth = 32.dp,
        minHeight = 32.dp
    ).getButtonSize()

    public val animation: ButtonAnimation = ButtonAnimationConfigure(
        duration = 300,
        easing = EaseInCirc
    ).getButtonAnimation()

    public val shape: ButtonShapes = ButtonShapeConfigure(
        cornerRadius = ButtonShape(
            disabled = 64.dp,
            focused = 64.dp,
            hovered = 16.dp,
            pressed = 16.dp,
            default = 64.dp,
        )
    ).getButtonShape()
}