package dev.yamh.io.presentation.feature.homes.homes.view

import StackedSnackbarHost
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListItemInfo
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import dev.jordond.connectivity.Connectivity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.core.ext.calculateCurrentOffsetForPage
import dev.yamh.io.presentation.core.ui.core.ext.toColor
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.EmptyCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.ErrorCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.LoadingSelectableCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.SelectableCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.ContentSwitcher
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Home32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.atom.indicator.PagerIndicatorOrientation
import dev.yamh.io.presentation.core.ui.source.kit.atom.indicator.PagerWormIndicator
import dev.yamh.io.presentation.core.ui.source.kit.atom.pager.VerticalCompressingPager
import dev.yamh.io.presentation.core.ui.source.kit.atom.stack.StackVertical
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionBar
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesIntent
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import rememberStackedSnackbarHostState
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.exp
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

@Composable
public fun HomesCompactView(
    onIntent: (HomesIntent) -> Unit,
    state: HomesState
) {

    val pagerState = rememberPagerState { state.content?.size ?: 0 }

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
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                VerticalCompressingPager(
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(Theme.spacing.space16),
                                    pagerState = pagerState,
                                    items = state.content ?: emptyList()
                                ) { item ->
                                    SelectableCard(
                                        modifier = Modifier,
                                        background = item.color?.toColor() ?: Color.Cyan,
                                        title = item.name.value,
                                        isSelected = true,
                                    ) {
                                        Box(modifier = Modifier.fillMaxSize()) {
                                            TertiaryIconButton(
                                                modifier = Modifier.fillMaxSize(),
                                                icon = LogIn24,
                                                onClick = {
                                                    onIntent(
                                                        HomesIntent.OnSelectHomeIntent(
                                                            item
                                                        )
                                                    )
                                                }
                                            )
                                        }
                                    }
                                }
                                PagerWormIndicator(
                                    modifier = Modifier,
                                    pagerState = pagerState,
                                    activeDotColor = Theme.color.accent1,
                                    dotColor = Theme.color.primary2,
                                    orientation = PagerIndicatorOrientation.Vertical
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


