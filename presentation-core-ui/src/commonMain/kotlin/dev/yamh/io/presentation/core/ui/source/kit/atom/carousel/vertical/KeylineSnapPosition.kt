package dev.yamh.io.presentation.core.ui.source.kit.atom.carousel.vertical


import androidx.compose.foundation.gestures.snapping.SnapPosition
import kotlin.math.max
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * Calculates the offset from the beginning of the carousel container needed to snap to the item at
 * [itemIndex].
 *
 * This method takes into account the correct keyline list needed to allow the item to be fully
 * visible and located at a focal position.
 */
internal fun getSnapPositionOffset(strategy: Strategy, itemIndex: Int, itemCount: Int): Int {
    if (!strategy.isValid) return 0

    val numOfFocalKeylines =
        strategy.defaultKeylines.lastFocalIndex - strategy.defaultKeylines.firstFocalIndex
    val startStepsSize = strategy.startKeylineSteps.size + numOfFocalKeylines
    val endStepsSize = strategy.endKeylineSteps.size + numOfFocalKeylines

    var offset =
        (strategy.defaultKeylines.firstFocal.unadjustedOffset - strategy.itemMainAxisSize / 2F)
            .roundToInt()

    if (itemIndex < startStepsSize) {
        var startIndex = max(0, startStepsSize - 1 - itemIndex)
        startIndex = min(strategy.startKeylineSteps.size - 1, startIndex)
        val startKeylines = strategy.startKeylineSteps[startIndex]
        offset =
            (startKeylines.firstFocal.unadjustedOffset - strategy.itemMainAxisSize / 2f)
                .roundToInt()
    }
    if (itemCount > numOfFocalKeylines + 1 && itemIndex >= itemCount - endStepsSize) {
        var endIndex = max(0, itemIndex - itemCount + endStepsSize)
        endIndex = min(strategy.endKeylineSteps.size - 1, endIndex)
        val endKeylines = strategy.endKeylineSteps[endIndex]
        offset =
            (endKeylines.firstFocal.unadjustedOffset - strategy.itemMainAxisSize / 2f).roundToInt()
    }

    return offset
}

internal fun KeylineSnapPosition(pageSize: CarouselPageSize): SnapPosition =
    object : SnapPosition {
        override fun position(
            layoutSize: Int,
            itemSize: Int,
            beforeContentPadding: Int,
            afterContentPadding: Int,
            itemIndex: Int,
            itemCount: Int
        ): Int {
            return getSnapPositionOffset(pageSize.strategy, itemIndex, itemCount)
        }
    }
