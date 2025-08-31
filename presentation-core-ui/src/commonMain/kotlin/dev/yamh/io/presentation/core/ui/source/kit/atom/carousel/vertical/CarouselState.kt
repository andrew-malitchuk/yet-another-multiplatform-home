package dev.yamh.io.presentation.core.ui.source.kit.atom.carousel.vertical

/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import androidx.annotation.FloatRange
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.gestures.ScrollScope
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect

/**
 * The state that can be used to control all types of carousels.
 *
 * @param currentItem the current item to be scrolled to.
 * @param currentItemOffsetFraction the offset of the current item as a fraction of the item's size.
 *   This should vary between -0.5 and 0.5 and indicates how to offset the current item from the
 *   snapped position.
 * @param itemCount the number of items this Carousel will have.
 */
public class CarouselState(
    currentItem: Int = 0,
    @FloatRange(from = -0.5, to = 0.5) currentItemOffsetFraction: Float = 0f,
    itemCount: () -> Int
) : ScrollableState {
    public var itemCountState: MutableState<() -> Int> = mutableStateOf(itemCount)

    internal var pagerState: PagerState =
        PagerState(currentItem, currentItemOffsetFraction, itemCountState.value)

    override val isScrollInProgress: Boolean
        get() = pagerState.isScrollInProgress

    override fun dispatchRawDelta(delta: Float): Float {
        return pagerState.dispatchRawDelta(delta)
    }

    override suspend fun scroll(
        scrollPriority: MutatePriority,
        block: suspend ScrollScope.() -> Unit
    ) {
        pagerState.scroll(scrollPriority, block)
    }

    public companion object {
        /** To keep current item and item offset saved */
        public val Saver: Saver<CarouselState, *> =
            listSaver(
                save = {
                    listOf(
                        it.pagerState.currentPage,
                        it.pagerState.currentPageOffsetFraction,
                        it.pagerState.pageCount,
                    )
                },
                restore = {
                    CarouselState(
                        currentItem = it[0] as Int,
                        currentItemOffsetFraction = it[1] as Float,
                        itemCount = { it[2] as Int },
                    )
                }
            )
    }
}

/**
 * Creates a [CarouselState] that is remembered across compositions.
 *
 * @param initialItem The initial item that should be scrolled to.
 * @param itemCount The number of items this Carousel will have.
 */
@Composable
public fun rememberCarouselState(
    initialItem: Int = 0,
    itemCount: () -> Int,
): CarouselState {
    return rememberSaveable(saver = CarouselState.Saver) {
        CarouselState(
            currentItem = initialItem,
            currentItemOffsetFraction = 0F,
            itemCount = itemCount
        )
    }
        .apply { itemCountState.value = itemCount }
}

/**
 * Interface to hold information about a Carousel item and its size.
 *
 * Example of CarouselItemInfo usage:
 *
 * @sample androidx.compose.material3.samples.FadingHorizontalMultiBrowseCarouselSample
 */
public sealed interface CarouselItemInfo {

    /** The size of the carousel item in the main axis */
    public val size: Float

    /**
     * The minimum size in the main axis of the carousel item, eg. the size of the item when it
     * scrolls off the sides of the carousel
     */
    public val minSize: Float

    /**
     * The maximum size in the main axis of the carousel item, eg. the size of the item when it is
     * at a focal position
     */
    public val maxSize: Float

    /** The rect by which the carousel item is being clipped. */
    public val maskRect: Rect

}

public class CarouselItemInfoImpl : CarouselItemInfo {

    public var sizeState: Float by mutableFloatStateOf(0f)
    public var minSizeState: Float by mutableFloatStateOf(0f)
    public var maxSizeState: Float by mutableFloatStateOf(0f)
    public var maskRectState: Rect by mutableStateOf(Rect.Zero)

    override val size: Float
        get() = sizeState

    override val minSize: Float
        get() = minSizeState

    override val maxSize: Float
        get() = maxSizeState

    override val maskRect: Rect
        get() = maskRectState

}


private const val SIZE_TOL_PX = 0.5f

public val CarouselItemInfoImpl.isFocal: Boolean
    get() = sizeState >= (maxSizeState - SIZE_TOL_PX)

public val CarouselItemInfoImpl.isCollapsed: Boolean
    get() = !isFocal