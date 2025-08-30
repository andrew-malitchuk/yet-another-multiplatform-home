package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.unit.Dp
import dev.yamh.io.presentation.core.ui.core.ext.has
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.core.ButtonInteractionState

public interface PushButtonShapes {

    @Stable
    @Composable
    public fun cornerRadius(interactionState: Int, enabled: Boolean): State<Dp>

}

public data class PushButtonShape(
    val disabled: Dp,
    val focused: Dp,
    val hovered: Dp,
    val pressed: Dp,
    val default: Dp
)

public data class PushButtonShapeConfigure(
    val cornerRadius: PushButtonShape,
) {
    public fun getButtonShape(): PushButtonShapes {
        return object : PushButtonShapes {
            @Composable
            override fun cornerRadius(interactionState: Int, enabled: Boolean): State<Dp> {
                ("interactionState: $interactionState, enabled: $enabled").toString()
                return rememberUpdatedState(
                    when {
                        !enabled -> cornerRadius.disabled
                        interactionState has PushButtonInteractionState.FOCUSED -> cornerRadius.focused
                        interactionState has PushButtonInteractionState.PRESSED -> cornerRadius.pressed
                        interactionState has PushButtonInteractionState.SELECTED -> cornerRadius.pressed
                        interactionState has PushButtonInteractionState.HOVER -> cornerRadius.hovered
                        else -> cornerRadius.default
                    }
                )
            }
        }
    }
}