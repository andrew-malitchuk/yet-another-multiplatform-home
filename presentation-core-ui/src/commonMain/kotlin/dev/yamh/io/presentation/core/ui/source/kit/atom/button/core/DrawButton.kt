package dev.yamh.io.presentation.core.ui.source.kit.atom.button.core

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.yamh.presentation.core.styling.core.Theme.size

@Composable
public fun DrawButton(
    icon: ImageVector,
    backgroundColor: Color,
    foregroundColor: Color,
    borderColor: Color,
    corner:Dp,
    iconSize: Dp,
    borderSize: Dp,
    minWidth: Dp,
    minHeight: Dp,
    paddings: PaddingValues,
    modifier: Modifier = Modifier
) {
    //
//    val interactionSource = remember { MutableInteractionSource() }
//    val isPressed = interactionSource.collectIsPressedAsState()
//    val radius = if (isPressed.value) {
//        10.dp
//    } else {
//        20.dp
//    }
//    val cornerRadius = animateDpAsState(targetValue = radius)


    //
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(
                width = borderSize,
                color = borderColor,
                shape = RoundedCornerShape(corner)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(corner)
            )
            .padding(paddings)
            .defaultMinSize(minWidth = minWidth, minHeight = minHeight)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = foregroundColor,
            modifier = Modifier.size(iconSize)
        )
    }
}