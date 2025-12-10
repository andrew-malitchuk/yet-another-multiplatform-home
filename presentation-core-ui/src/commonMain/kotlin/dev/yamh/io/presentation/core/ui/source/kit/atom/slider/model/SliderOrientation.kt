package dev.yamh.io.presentation.core.ui.source.kit.atom.slider.model

/**
 * The [SliderOrientation] class is responsible for controlling the dragging behavior of the slider component.
 */
public sealed class SliderOrientation {
    /**
     * [Vertical] makes slider progression to be changed by dragging up or down along the y-axis.
     */
    public object Vertical : SliderOrientation()
    /**
     * [Horizontal] makes slider progression to be changed by dragging right or left along the x-axis.
     */
    public object Horizontal: SliderOrientation()
}
