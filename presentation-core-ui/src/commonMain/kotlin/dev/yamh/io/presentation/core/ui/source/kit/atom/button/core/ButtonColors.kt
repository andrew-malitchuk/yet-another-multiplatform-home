package dev.yamh.io.presentation.core.ui.source.kit.atom.button.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import dev.yamh.io.presentation.core.ui.core.ext.has

public interface ButtonColors {
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

public data class ButtonColor(
    val disabledColor: Color,
    val focusedColor: Color,
    val hoveredColor: Color,
    val pressedColor: Color,
    val defaultColor: Color
)

public data class ButtonColorConfigure(
    val borderColor: ButtonColor,
    val foregroundColor: ButtonColor,
    val backgroundColor: ButtonColor,
) {
    public fun getButtonColors(): ButtonColors {
        return object : ButtonColors {
            @Composable
            override fun borderColor(interactionState: Int, enabled: Boolean): State<Color> {
                return rememberUpdatedState(
                    when {
                        !enabled -> borderColor.disabledColor
                        interactionState has ButtonInteractionState.FOCUSED -> borderColor.focusedColor
                        interactionState has ButtonInteractionState.HOVER -> borderColor.hoveredColor
                        else -> borderColor.defaultColor
                    }
                )
            }

            @Composable
            override fun foregroundColor(interactionState: Int, enabled: Boolean): State<Color> {
                return rememberUpdatedState(
                    when {
                        !enabled -> foregroundColor.disabledColor
                        interactionState has ButtonInteractionState.HOVER -> foregroundColor.hoveredColor
                        interactionState has ButtonInteractionState.PRESSED -> foregroundColor.pressedColor
                        else -> foregroundColor.defaultColor
                    }
                )
            }

            @Composable
            override fun backgroundColor(interactionState: Int, enabled: Boolean): State<Color> {
                return rememberUpdatedState(
                    when {
                        !enabled -> backgroundColor.disabledColor
                        interactionState has ButtonInteractionState.PRESSED -> backgroundColor.pressedColor
                        interactionState has ButtonInteractionState.HOVER -> backgroundColor.hoveredColor
                        else -> backgroundColor.defaultColor
                    }
                )
            }
        }
    }
}