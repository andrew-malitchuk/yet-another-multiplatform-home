package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp

@Composable
public fun DrawPushButton(
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
    modifier: Modifier = Modifier
) {
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