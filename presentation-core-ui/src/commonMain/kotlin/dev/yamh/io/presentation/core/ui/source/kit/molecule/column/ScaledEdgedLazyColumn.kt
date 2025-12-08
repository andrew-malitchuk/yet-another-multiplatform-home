package dev.yamh.io.presentation.core.ui.source.kit.molecule.column

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.divider.HorizontalAnimatedDivider
import dev.yamh.presentation.core.styling.core.Theme
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.pow

public data class GridScalingParams(
    val enabled: Boolean = true,
    val onlyWhenScrolling: Boolean = true,
    val minScale: Float = 0.88f,
    val maxScale: Float = 1f,
    val minAlpha: Float = 0.9f,
    val maxAlpha: Float = 1f,
    val pivotY: Float = 0.5f,
    val falloffExponent: Float = 0.5f,
    val animationDurationMs: Int = 150
)

@Composable
public fun Modifier.scalingOnGridScroll(
    state: LazyGridState,
    index: Int,
    params: GridScalingParams = GridScalingParams()
): Modifier {
    if (!params.enabled) return this

    var targetScale = 1f
    var targetAlpha = 1f

    val layout = state.layoutInfo
    val info = layout.visibleItemsInfo.firstOrNull { it.index == index }

    if (params.onlyWhenScrolling && !state.isScrollInProgress) {
        targetScale = 1f
        targetAlpha = 1f
    } else if (info != null) {
        val viewportStart = layout.viewportStartOffset.toFloat()
        val viewportEnd = layout.viewportEndOffset.toFloat()
        val viewportCenter = (viewportStart + viewportEnd) / 2f

        val itemCenterY = info.offset.y + info.size.height / 2f
        val dist = abs(itemCenterY - viewportCenter)
        val halfHeight = (viewportEnd - viewportStart) / 2f
        val norm = min(1f, dist / halfHeight)
        val t = norm.pow(params.falloffExponent)

        targetScale = params.maxScale + (params.minScale - params.maxScale) * t
        targetAlpha = params.maxAlpha + (params.minAlpha - params.maxAlpha) * t
    }

    val animatedScale by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = androidx.compose.animation.core.tween(params.animationDurationMs),
        label = "scaleAnim"
    )

    val animatedAlpha by animateFloatAsState(
        targetValue = targetAlpha,
        animationSpec = androidx.compose.animation.core.tween(params.animationDurationMs),
        label = "alphaAnim"
    )

    return this.then(
        Modifier.graphicsLayer {
            scaleX = animatedScale
            scaleY = animatedScale
            alpha = animatedAlpha
            transformOrigin = TransformOrigin(params.pivotY, params.pivotY)
        }
    )
}

public fun <T> LazyGridScope.itemsScaled(
    state: LazyGridState,
    items: List<T>,
    key: ((T) -> Any)? = null,
    params: GridScalingParams = GridScalingParams(),
    itemContent: @Composable LazyGridItemScope.(T) -> Unit
) {
    itemsIndexed(
        items = items,
        key = if (key != null) { _, item -> key(item) } else null
    ) { index, item ->
        Box(
            Modifier.scalingOnGridScroll(
                state = state,
                index = index,
                params = params
            )
        ) {
            itemContent(item)
        }
    }
}

public fun <T> LazyGridScope.itemsIndexedScaled(
    state: LazyGridState,
    items: List<T>,
    key: ((Int, T) -> Any)? = null,
    params: GridScalingParams = GridScalingParams(),
    itemContent: @Composable LazyGridItemScope.(index: Int, T) -> Unit
) {
    itemsIndexed(items = items, key = key) { index, item ->
        Box(
            Modifier.scalingOnGridScroll(
                state = state,
                index = index,
                params = params
            )
        ) {
            itemContent(index, item)
        }
    }
}


@Composable
public fun ScaledEdgedLazyColumn(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    state: LazyGridState = rememberLazyGridState(),
    scalingParams: GridScalingParams = GridScalingParams(),
    verticalSpacing: Int = 8,
    horizontalSpacing: Int = 8,
    content: LazyGridScope.(state: LazyGridState, scaling: GridScalingParams) -> Unit
) {
    Column(modifier = modifier.fillMaxSize()) {
        val fling = rememberSnapFlingBehavior(
            lazyGridState = state,
            snapPosition = SnapPosition.Start
        )

        // Top separator if you want one:
        HorizontalAnimatedDivider(
            isVisible = state.canScrollBackward
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = Theme.spacing.space8)
                .weight(1f),
            columns = GridCells.Fixed(columns),
            state = state,
            flingBehavior = fling,
            verticalArrangement = Arrangement.spacedBy(verticalSpacing.dp),
            horizontalArrangement = Arrangement.spacedBy(horizontalSpacing.dp)
        ) {
            content(state, scalingParams)
        }

        // Bottom separator if you want one:
        HorizontalAnimatedDivider(
            isVisible = state.canScrollForward
        )
    }
}