package dev.yamh.io.presentation.core.ui.core.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

/**
 * Creates a new modifier that applies a clickable behavior to the composable without the
 * default ripple effect.
 *
 * @param onClick An optional lambda function that defines the action to be performed when the
 *        user clicks on the composable. Can be null if click behavior is not desired.
 * @return A new `Modifier` instance with the clickable behavior applied (if `onClick` is not
 *         null) or the original modifier unchanged (if `onClick` is null).
 */
public fun Modifier.noRippleClickable(onClick: (() -> Unit)? = null): Modifier =
    composed {
        if (onClick != null) {
            this.clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
            ) {
                onClick.invoke()
            }
        } else {
            this
        }
    }
