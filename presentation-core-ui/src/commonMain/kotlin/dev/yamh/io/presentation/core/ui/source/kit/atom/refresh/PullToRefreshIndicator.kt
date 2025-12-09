package dev.yamh.io.presentation.core.ui.source.kit.atom.refresh

import ArrowUp32
import RotateCw32
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Check32
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun PullToRefreshIndicator(
    state: PullToRefreshState,
    refreshTriggerDistance: Dp,
    refreshingOffset: Dp,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = Theme.typography.button.copy(
        color = Theme.color.primary2
    ),
) {
    val refreshTriggerPx = with(LocalDensity.current) { refreshTriggerDistance.toPx() }
    val refreshingOffsetPx = with(LocalDensity.current) { refreshingOffset.toPx() }
    val indicatorHeight = 48.dp
    val indicatorHeightPx = with(LocalDensity.current) { indicatorHeight.toPx() }

    val localisation = LocalLocalisation.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(indicatorHeight)
            .padding(end = Theme.spacing.space16)
            .graphicsLayer {
                translationY = state.contentOffset - (refreshingOffsetPx + indicatorHeightPx) / 2
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (state.isRefreshing) {
            val transition = rememberInfiniteTransition()
            val progress by transition.animateValue(
                0f,
                1f,
                Float.VectorConverter,
                infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1332, // 1 and 1/3 second
                        easing = LinearEasing
                    )
                )
            )
            Image(
                imageVector = RotateCw32,
                contentDescription = "refreshing",
                modifier = Modifier
                    .padding(end = Theme.spacing.space8)
                    .size(Theme.spacing.space16)
                    .rotate(progress * 360),
                colorFilter = ColorFilter.tint(textStyle.color)
            )
        } else {
            val progress = ((state.contentOffset - refreshTriggerPx / 2) / refreshTriggerPx * 2)
                .coerceIn(0f, 1f)
            Image(
                imageVector = ArrowUp32,
                contentDescription = "pull to refresh",
                modifier = Modifier
                    .padding(end = Theme.spacing.space8)
                    .size(Theme.spacing.space16)
                    .rotate(progress * 180),
                colorFilter = ColorFilter.tint(textStyle.color)
            )
        }
        BasicText(
            text =
                when {
                    state.isPullInProgress && state.contentOffset >= refreshTriggerPx -> localisation.general.releaseToRefresh
                    state.isRefreshing -> localisation.general.refreshing
                    else -> localisation.general.pullToRefresh
                },
            style = textStyle
        )
    }
}