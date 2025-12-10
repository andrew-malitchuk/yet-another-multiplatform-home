package dev.yamh.io.presentation.core.ui.source.kit.atom.slider.model

/**
 * [SliderType] makes slider progression to be [Discrete] or [Continuous]
 */
public sealed class SliderType {
    /**
     * [Discrete] makes slider progression changes discretely.
     */
    public data class Discrete(val steps: Int) : SliderType()

    /**
     * [Continuous] makes slider progression changes continuously.
     */
    public object Continuous : SliderType()
}
