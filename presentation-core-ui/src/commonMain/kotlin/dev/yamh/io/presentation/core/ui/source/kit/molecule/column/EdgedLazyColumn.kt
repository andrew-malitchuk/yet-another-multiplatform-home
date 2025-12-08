package dev.yamh.io.presentation.core.ui.source.kit.molecule.column

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.divider.HorizontalAnimatedDivider
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun EdgedLazyColumn(
    modifier: Modifier = Modifier,
    columns: Int = 2,
    content: LazyGridScope.() -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        val state = rememberLazyGridState()
        val fling = rememberSnapFlingBehavior(
            lazyGridState = state,
            snapPosition = SnapPosition.Start
        )
        HorizontalAnimatedDivider(
            isVisible = state.canScrollBackward
        )
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            columns = GridCells.Fixed(columns),
            flingBehavior = fling,
            state = state,
            verticalArrangement = Arrangement.spacedBy(Theme.spacing.space8),
            horizontalArrangement = Arrangement.spacedBy(Theme.spacing.space8),
            content = content
        )
        HorizontalAnimatedDivider(
            isVisible = state.canScrollForward
        )
    }
}