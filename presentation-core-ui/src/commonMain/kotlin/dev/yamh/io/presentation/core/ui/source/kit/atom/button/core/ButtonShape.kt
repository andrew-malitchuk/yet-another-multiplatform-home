package dev.yamh.io.presentation.core.ui.source.kit.atom.button.core

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import dev.yamh.io.presentation.core.ui.core.ext.has


public interface ButtonShapes {

    @Stable
    @Composable
    public fun cornerRadius(interactionState: Int, enabled: Boolean): State<Dp>

}

public data class ButtonShape(
    val disabled: Dp,
    val focused: Dp,
    val hovered: Dp,
    val pressed: Dp,
    val default: Dp
)

public data class ButtonShapeConfigure(
    val cornerRadius: ButtonShape,
) {
    public fun getButtonShape(): ButtonShapes {
        return object : ButtonShapes {
            @Composable
            override fun cornerRadius(interactionState: Int, enabled: Boolean): State<Dp> {
                ("interactionState: $interactionState, enabled: $enabled").toString()
                return rememberUpdatedState(
                    when {
                        !enabled -> cornerRadius.disabled
                        interactionState has ButtonInteractionState.FOCUSED -> cornerRadius.focused
                        interactionState has ButtonInteractionState.PRESSED -> cornerRadius.pressed
                        interactionState has ButtonInteractionState.HOVER -> cornerRadius.hovered
                        else -> cornerRadius.default
                    }
                )
            }
        }
    }
}