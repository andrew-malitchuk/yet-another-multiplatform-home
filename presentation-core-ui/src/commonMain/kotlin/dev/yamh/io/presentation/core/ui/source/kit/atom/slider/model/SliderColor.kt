package dev.yamh.io.presentation.core.ui.source.kit.atom.slider.model

import androidx.compose.ui.graphics.Color

/**
 * [enabledTrackColor]: Track color when the slider is enabled.
 * [disabledTrackColor]: Track color when the slider is disabled.
 * [enabledIndicationColor]: Indication color for progression when the slider is enabled
 * [disabledIndicationColor]: Indication color for progression when the slider is disabled.
 */
public data class SliderColor(
    val enabledTrackColor: Color = Color.Gray,
    val disabledTrackColor: Color = enabledTrackColor,
    val enabledIndicationColor: Color = Color.Black,
    val disabledIndicationColor: Color = enabledIndicationColor
) {
    public fun getTrackColor(isEnabled: Boolean): Color = if (isEnabled) {
        enabledTrackColor
    } else {
        disabledTrackColor
    }

    public fun getIndicationColor(isEnabled: Boolean): Color = if (isEnabled) {
        enabledIndicationColor
    } else {
        disabledIndicationColor
    }
}
