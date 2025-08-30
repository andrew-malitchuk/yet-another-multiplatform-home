package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
public fun AnimatePushButton(
    icon: ImageVector,
    backgroundColor: Color,
    foregroundColor: Color,
    borderColor: Color,
    corner: Dp,
    iconSize: Dp,
    borderSize: Dp,
    minWidth: Dp,
    minHeight: Dp,
    paddings: PaddingValues,
    animationDuration: Int,
    animationEasing: Easing,
    modifier: Modifier = Modifier
) {
    val colorAnimationSpec =
        tween<Color>(durationMillis = animationDuration, easing = animationEasing)
    val dpAnimationSpec =
        tween<Dp>(durationMillis = animationDuration, easing = animationEasing)

    val animatedBorderColor by animateColorAsState(
        animationSpec = colorAnimationSpec,
        targetValue = borderColor,
        label = "border"
    )
    val animatedBackgroundColor by animateColorAsState(
        animationSpec = colorAnimationSpec,
        targetValue = backgroundColor,
        label = "background"
    )
    val animatedForegroundColor by animateColorAsState(
        animationSpec = colorAnimationSpec,
        targetValue = foregroundColor,
        label = "foreground"
    )

    val animatedCorner by animateDpAsState(
        animationSpec = dpAnimationSpec,
        targetValue = corner,
        label = "corner"
    )

    val localModifier = modifier.animateContentSize(
        animationSpec = tween(
            durationMillis = animationDuration,
            easing = animationEasing
        )
    )

    DrawPushButton(
        icon = icon,
        backgroundColor = animatedBackgroundColor,
        foregroundColor = animatedForegroundColor,
        borderColor = animatedBorderColor,
        corner = animatedCorner,
        iconSize = iconSize,
        borderSize = borderSize,
        minWidth = minWidth,
        minHeight = minHeight,
        paddings = paddings,
        modifier = localModifier
    )
}