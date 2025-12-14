package dev.yamh.io.presentation.feature.homes.homes.view

import StackedSnackbarHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.jordond.connectivity.Connectivity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.core.ext.toColor
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.EmptyCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.ErrorCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.LoadingSelectableCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.SelectableCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.ContentSwitcher
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.atom.stack.StackHorizontalAnimated
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionBar
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesIntent
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun HomesExpandedView(
    onIntent: (HomesIntent) -> Unit,
    state: HomesState
) {
    val localisation = LocalLocalisation.current

    val stackedSnackbarHostState =
        rememberStackedSnackbarHostState(
            animation = StackedSnackbarAnimation.Slide,
            maxStack = 5,
        )

    val coroutineScope = rememberCoroutineScope()
    val connectivity = Connectivity()
    connectivity.start()
    coroutineScope.launch {
        connectivity.statusUpdates.collect { status ->
            if (status is Connectivity.Status.Disconnected) {
                stackedSnackbarHostState.showSnackbar(
                    title = localisation.general.networkError,
                )
            }
        }
    }

    SafeContainer(
        snackbarHost = {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                StackedSnackbarHost(hostState = stackedSnackbarHostState)
            }
        },
    ) {
        AnimationSequenceHost {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                AnimatedItem(
                    index = 0,
                    enter = slideInVertically(
                        tween(
                            durationMillis = 250,
                        )
                    ) { fullHeight -> -fullHeight }
                ) {
                    TopActionBar(
                        icon = Settings32,
                    ) { onIntent(HomesIntent.GoToSettingsIntent) }
                }
                AnimatedItem(
                    index = 1,
                    enter = slideInVertically(
                        tween(
                            durationMillis = 250,
                        )
                    ) { fullHeight -> +fullHeight }
                ) {
                    ContentSwitcher(
                        modifier = Modifier.fillMaxSize(),
                        isLoading = state.isLoading,
                        isRefreshing = state.isRefreshing,
                        isError = state.isError,
                        isEmpty = state.content?.isEmpty() == true,
                        onRefresh = {
                            onIntent(HomesIntent.RefreshIntent)
                        },
                    ) {
                        StackHorizontalAnimated(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(3.5f)
                                .padding(top = Theme.spacing.space8)
                                .padding(Theme.spacing.space8),
                            stackedState = false,
                        ) {
                            items((state.content?:emptyList())) { index, content, selected ->
                                SelectableCard(
                                    modifier = Modifier,
                                    background = content.color?.toColor() ?: Color.Cyan,
                                    title = content.name.value,
                                    isSelected = true,
                                ) {
                                    Box(modifier = Modifier.fillMaxSize()) {
                                        TertiaryIconButton(
                                            modifier = Modifier.fillMaxSize(),
                                            icon = LogIn24,
                                            onClick = {
                                                onIntent(HomesIntent.OnSelectHomeIntent(content))
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

