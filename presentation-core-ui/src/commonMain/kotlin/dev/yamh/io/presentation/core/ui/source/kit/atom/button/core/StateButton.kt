package dev.yamh.io.presentation.core.ui.source.kit.atom.button.core

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
public fun StateButton(
    onClick: () -> Unit,
    icon: ImageVector,
    colors: ButtonColors,
    sizes: ButtonSizes,
    shape: ButtonShapes,
//    shape: Shape,
    animation: ButtonAnimation,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val isHovered by interactionSource.collectIsHoveredAsState()
    val isPressed by interactionSource.collectIsPressedAsState()
    val isFocused by interactionSource.collectIsFocusedAsState()

    var interactionState = 0
    if (isHovered) interactionState = interactionState.or(ButtonInteractionState.HOVER)
    if (isPressed) interactionState = interactionState.or(ButtonInteractionState.PRESSED)
    if (isFocused) interactionState = interactionState.or(ButtonInteractionState.FOCUSED)

    val currentModifier = modifier.clickable(
        interactionSource = interactionSource,
        indication = null,
        enabled = enabled,
        onClick = onClick,
        role = Role.Button
    )

    val backgroundColor = colors.backgroundColor(interactionState, enabled).value
    val foregroundColor = colors.foregroundColor(interactionState, enabled).value
    val borderColor = colors.borderColor(interactionState, enabled).value
    val cornerRadius = shape.cornerRadius(interactionState, enabled).value

    ("cornerRadius: $cornerRadius, ${cornerRadius.value}").toString()

    AnimateButton(
        icon = icon,
        backgroundColor = backgroundColor,
        foregroundColor = foregroundColor,
        borderColor = borderColor,
        corner = cornerRadius,
        iconSize = sizes.iconSize,
        borderSize = sizes.borderSize,
        minWidth = sizes.minWidth,
        minHeight = sizes.minHeight,
        paddings = sizes.contentPadding,
        animationDuration = animation.duration,
        animationEasing = animation.easing,
        modifier = currentModifier
    )
}
