package dev.yamh.io.presentation.core.ui.source.kit.atom.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.EmptyCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.ErrorCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.LoadingSelectableCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.refresh.PullToRefresh
import dev.yamh.io.presentation.core.ui.source.kit.atom.refresh.rememberPullToRefreshState
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun ContentSwitcher(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    isRefreshing: Boolean = false,
    isError: Boolean,
    isEmpty: Boolean,
    onRefresh: () -> Unit,
    loadingContent: (@Composable () -> Unit)? = {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            LoadingSelectableCard(
                modifier = Modifier
                    .padding(
                        start = Theme.spacing.space16,
                        end = Theme.spacing.space24
                    ),
                background = Theme.color.primary0
            )
        }
    },
    errorContent: (@Composable () -> Unit)? = {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            ErrorCard(
                modifier = Modifier
                    .padding(
                        start = Theme.spacing.space16,
                        end = Theme.spacing.space24
                    ),
                background = Theme.color.primary0
            )
        }
    },
    emptyContent: (@Composable () -> Unit)? = {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            EmptyCard(
                modifier = Modifier
                    .padding(16.dp),
                background = Theme.color.primary0,
            )
        }

    },
    content: @Composable () -> Unit,
) {

    println("ContentSwitcher: isLoading=$isLoading, isRefreshing=$isRefreshing, isError=$isError, isEmpty=$isEmpty")

    PullToRefresh(
        state = rememberPullToRefreshState(isRefreshing = isRefreshing),
        onRefresh = { onRefresh() },
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            val target = when {
                // explicit: if both empty and loading, show loading
                isEmpty && isLoading -> 0
                isEmpty && isError -> 0
                isLoading -> 0
                isError -> 1
                isEmpty -> 2
                else -> 3
            }

            androidx.compose.animation.Crossfade(targetState = target) { state ->
                when (state) {
                    0 -> loadingContent?.invoke() ?: content()
                    1 -> errorContent?.invoke() ?: content()
                    2 -> emptyContent?.invoke() ?: content()
                    else -> content()
                }
            }
        }

    }
}