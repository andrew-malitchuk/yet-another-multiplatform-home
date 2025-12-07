package dev.yamh.io.presentation.core.ui.source.kit.molecule.row

import androidx.compose.animation.core.snap
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.divider.VerticalAnimatedDivider
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun EdgedLazyRow(
    modifier: Modifier = Modifier,
    content: LazyListScope.() -> Unit
) {
    val state = rememberLazyListState()
    val fling = rememberSnapFlingBehavior(lazyListState = state, snapPosition = SnapPosition.Start)
    Row(
        modifier = modifier
    ) {
        VerticalAnimatedDivider(
            modifier = Modifier
                .fillMaxHeight(),
            isVisible = state.canScrollBackward
        )
        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            flingBehavior = fling,
            state = state,
            horizontalArrangement = Arrangement.spacedBy(Theme.spacing.space8),
            content = content,
        )
        VerticalAnimatedDivider(
            modifier = Modifier
                .fillMaxHeight(),
            isVisible = state.canScrollForward
        )

    }

}