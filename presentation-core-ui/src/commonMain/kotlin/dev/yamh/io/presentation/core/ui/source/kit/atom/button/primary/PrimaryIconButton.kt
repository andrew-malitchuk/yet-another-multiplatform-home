package dev.yamh.io.presentation.core.ui.source.kit.atom.button.primary

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonAnimation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonColors
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonShapes
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonSizes
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.StateButton

@Composable
public fun PrimaryIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    enabled: Boolean = true,
    shape: ButtonShapes = PrimaryButtonDefaults.shape,
    colors: ButtonColors = PrimaryButtonDefaults.colors,
    sizes: ButtonSizes = PrimaryButtonDefaults.sizes,
    animation: ButtonAnimation = PrimaryButtonDefaults.animation,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit,
) {
    StateButton(
        onClick,
        icon,
        colors,
        sizes,
        shape,
        animation,
        modifier,
        enabled,
        interactionSource
    )
}
