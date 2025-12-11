package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push

import androidx.compose.animation.core.EaseInCirc
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonAnimation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonAnimationConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonColor
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonColorConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonColors
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonShapeConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonShapes
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonSizeConfigure
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonSizes
import dev.yamh.presentation.core.styling.core.Theme

public object PushButtonDefaults {
    public val colors: PushButtonColors
        @Composable
        get() = PushButtonColorConfigure(
            foregroundColor = PushButtonColor(
                disabledColor = Theme.color.primary0,
                focusedColor = Theme.color.secondary1,
                hoveredColor = Theme.color.secondary1,
                pressedColor = Theme.color.secondary1,
                selectedColor = Theme.color.primary0,
                defaultColor = Theme.color.primary0
            ),
            backgroundColor = PushButtonColor(
                disabledColor = Theme.color.primary1,
                focusedColor = Theme.color.primary2,
                hoveredColor = Theme.color.primary2,
                pressedColor = Theme.color.accent0,
                selectedColor = Theme.color.accent1,
                defaultColor = Theme.color.primary1,
            ),
            borderColor = PushButtonColor(
                disabledColor = Color.Transparent,
                focusedColor = Color.Transparent,
                hoveredColor = Color.Transparent,
                pressedColor = Color.Transparent,
                selectedColor = Color.Transparent,
                defaultColor = Color.Transparent
            )
        ).getButtonColors()

    public val sizes: PushButtonSizes = PushButtonSizeConfigure(
        iconSize = 24.dp,
        borderSize = 0.dp,
        contentPadding = PaddingValues(8.dp),
        minWidth = 32.dp,
        minHeight = 32.dp
    ).getPushButtonSize()

    public val animation: PushButtonAnimation = PushButtonAnimationConfigure(
        duration = 300,
        easing = EaseInCirc
    ).getButtonAnimation()

    public val shape: PushButtonShapes = PushButtonShapeConfigure(
        cornerRadius = PushButtonShape(
            disabled =  1024.dp,
            focused = 1024.dp,
            hovered = 32.dp,
            pressed = 32.dp,
            default = 1024.dp,
        )
    ).getButtonShape()
}