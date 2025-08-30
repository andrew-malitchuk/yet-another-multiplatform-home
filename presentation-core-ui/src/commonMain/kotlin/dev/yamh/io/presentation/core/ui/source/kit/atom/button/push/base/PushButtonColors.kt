package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import dev.yamh.io.presentation.core.ui.core.ext.has

public interface PushButtonColors {
    @Stable
    @Composable
    public fun borderColor(interactionState: Int, enabled: Boolean): State<Color>

    @Stable
    @Composable
    public fun foregroundColor(interactionState: Int, enabled: Boolean): State<Color>

    @Stable
    @Composable
    public fun backgroundColor(interactionState: Int, enabled: Boolean): State<Color>
}

public data class PushButtonColor(
    val disabledColor: Color,
    val focusedColor: Color,
    val hoveredColor: Color,
    val pressedColor: Color,
    val selectedColor: Color,
    val defaultColor: Color
)

public data class PushButtonColorConfigure(
    val borderColor: PushButtonColor,
    val foregroundColor: PushButtonColor,
    val backgroundColor: PushButtonColor,
) {
    public fun getButtonColors(): PushButtonColors {
        return object : PushButtonColors {
            @Composable
            override fun borderColor(interactionState: Int, enabled: Boolean): State<Color> {
                return rememberUpdatedState(
                    when {
                        !enabled -> borderColor.disabledColor
                        interactionState has PushButtonInteractionState.FOCUSED -> borderColor.focusedColor
                        interactionState has PushButtonInteractionState.HOVER -> borderColor.hoveredColor
                        else -> borderColor.defaultColor
                    }
                )
            }

            @Composable
            override fun foregroundColor(interactionState: Int, enabled: Boolean): State<Color> {
                return rememberUpdatedState(
                    when {
                        !enabled -> foregroundColor.disabledColor
                        interactionState has PushButtonInteractionState.HOVER -> foregroundColor.hoveredColor
                        interactionState has PushButtonInteractionState.PRESSED -> foregroundColor.pressedColor
                        else -> foregroundColor.defaultColor
                    }
                )
            }

            @Composable
            override fun backgroundColor(interactionState: Int, enabled: Boolean): State<Color> {
                return rememberUpdatedState(
                    when {
                        !enabled -> backgroundColor.disabledColor
                        interactionState has PushButtonInteractionState.PRESSED -> backgroundColor.pressedColor
                        interactionState has PushButtonInteractionState.SELECTED -> backgroundColor.selectedColor
                        interactionState has PushButtonInteractionState.HOVER -> backgroundColor.hoveredColor
                        else -> backgroundColor.defaultColor
                    }
                )
            }
        }
    }
}