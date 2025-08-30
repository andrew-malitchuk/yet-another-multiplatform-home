package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonAnimation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonColors
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonShapes
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.PushButtonSizes
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base.StatePushButton

@Composable
public fun PushButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    enabled: Boolean = true,
    isSelected: Boolean = true,
    shape: PushButtonShapes = PushButtonDefaults.shape,
    colors: PushButtonColors = PushButtonDefaults.colors,
    sizes: PushButtonSizes = PushButtonDefaults.sizes,
    animation: PushButtonAnimation = PushButtonDefaults.animation,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {
    StatePushButton(
        onClick,
        icon,
        colors,
        sizes,
        shape,
        animation,
        modifier,
        enabled,
        isSelected,
        interactionSource,
    )
}
