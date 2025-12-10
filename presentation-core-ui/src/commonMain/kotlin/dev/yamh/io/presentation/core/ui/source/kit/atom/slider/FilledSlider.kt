package dev.yamh.io.presentation.core.ui.source.kit.atom.slider

import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import dev.yamh.io.presentation.core.ui.source.kit.atom.slider.continuous.ContinuousSlider
import dev.yamh.io.presentation.core.ui.source.kit.atom.slider.discrete.DiscreteSlider
import dev.yamh.io.presentation.core.ui.source.kit.atom.slider.discrete.DiscreteSliderCalculator
import dev.yamh.io.presentation.core.ui.source.kit.atom.slider.model.SliderColor
import dev.yamh.io.presentation.core.ui.source.kit.atom.slider.model.SliderOrientation
import dev.yamh.io.presentation.core.ui.source.kit.atom.slider.model.SliderType
import dev.yamh.presentation.core.styling.core.Theme

/**
 * [modifier]: The [Modifier] to be applied to this Slider.
 * [sliderShape]: [Shape] applied to the Slider.
 * [isEnabled]: Change slider enabled state. If disabled color is set to
 * [sliderColor]: Colors applied to the Slider.
 * [sliderOrientation]: Orientation for Slider. Vertical or Horizontal.
 * [sliderType]: Continuous and Discrete types are supported.
 * [dragSensitivity]: Drag sensitivity for slider. If the value is 1, the slider moves only as much as it is dragged.
 * [valueRange]: Value range for slider
 * [currentValue]: Current value for the Slider. It's forced to be in the range of [maxValue] and [minValue].
 * [setCurrentValue]: Callback in which value should be updated
 */
@Composable
public fun FilledSlider(
    modifier: Modifier,
    sliderShape: Shape = SquircleShape(50),
    isEnabled: Boolean = true,
    sliderColor: SliderColor = SliderColor(
        enabledTrackColor = Theme.color.primary2,
        enabledIndicationColor = Theme.color.secondary0
    ),
    sliderOrientation: SliderOrientation = SliderOrientation.Vertical,
    sliderType: SliderType = SliderType.Continuous,
    dragSensitivity: Float = 1f,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    currentValue: Float,
    setCurrentValue: (Float) -> Unit
) {
    val sliderLengthCalculator: SliderLengthCalculator by rememberUpdatedState(
        newValue = SliderLengthCalculator(
            maxValue = valueRange.endInclusive,
            minValue = valueRange.start
        )
    )

    when (sliderType) {
        is SliderType.Continuous -> {
            ContinuousSlider(
                modifier = modifier,
                sliderShape = sliderShape,
                isEnabled = isEnabled,
                sliderColor = sliderColor,
                sliderOrientation = sliderOrientation,
                dragSensitivity = dragSensitivity,
                sliderLengthCalculator = sliderLengthCalculator,
                valueRange = valueRange,
                currentValue = currentValue,
                setCurrentValue = setCurrentValue
            )
        }

        is SliderType.Discrete   -> {
            val discreteSliderCalculator by rememberUpdatedState(
                newValue = DiscreteSliderCalculator(
                    valueRange.endInclusive,
                    valueRange.start,
                    sliderType.steps
                )
            )
            DiscreteSlider(
                modifier = modifier,
                sliderShape = sliderShape,
                isEnabled = isEnabled,
                sliderColor = sliderColor,
                sliderOrientation = sliderOrientation,
                dragSensitivity = dragSensitivity,
                sliderLengthCalculator = sliderLengthCalculator,
                discreteSliderCalculator = discreteSliderCalculator,
                currentValue = discreteSliderCalculator.calculateCurrentSectionLength(currentValue),
                valueRange = valueRange,
                setCurrentValue = setCurrentValue
            )
        }
    }
}
