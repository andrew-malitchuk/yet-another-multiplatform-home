package dev.yamh.io.presentation.core.ui.source.kit.atom.slider.discrete

public class DiscreteSliderCalculator(
    maxValue: Float,
    minValue: Float,
    steps: Int
) {
    private val valueLength = maxValue - minValue

    private val avoidDecimalMisMatchValue = valueLength / 10000f

    private val numSections = steps - 1
    private val sectionLength = valueLength / numSections

    public fun getSectionCount(length: Float): Int {
        return ((length + avoidDecimalMisMatchValue) / sectionLength).toInt()
    }

    public fun getSectionLength(sectionCount: Int): Float {
        return sectionLength * sectionCount
    }

    public fun calculateCurrentSectionLength(length: Float): Float {
        val sections = getSectionCount(length)
        return getSectionLength(sections)
    }
}