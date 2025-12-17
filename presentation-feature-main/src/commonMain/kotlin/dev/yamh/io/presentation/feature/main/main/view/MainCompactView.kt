package dev.yamh.io.presentation.feature.main.main.view

import StackedSnackbarHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import dev.jordond.connectivity.Connectivity
import dev.yamh.domain.core.source.model.device.GeneralDeviceType
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.core.ext.toColor
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.PushButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.ContentSwitcher
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.divider.HorizontalAnimatedDivider
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Home32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.atom.indicator.PagerIndicatorOrientation
import dev.yamh.io.presentation.core.ui.source.kit.atom.indicator.PagerWormIndicator
import dev.yamh.io.presentation.core.ui.source.kit.atom.pager.VerticalCompressingPager
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionAdvancedBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.ContactDevice
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.TemperatureDevice
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.WindowCoveringDevice
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.core.ui.source.kit.organism.card.DeviceCard
import dev.yamh.io.presentation.core.ui.source.kit.organism.card.RoomCard
import dev.yamh.io.presentation.feature.main.core.util.toDeviceType
import dev.yamh.io.presentation.feature.main.main.mvi.MainIntent
import dev.yamh.io.presentation.feature.main.main.mvi.MainState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun MainCompactView(
    state: MainState,
    onIntent: (MainIntent) -> Unit,
) {

    val pagerState = rememberPagerState { state.rooms?.size ?: 0 }

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
                    TopActionAdvancedBar(
                        text = state.selectedHome?.name?.value ?: "",
                        action = Settings32,
                        navigation = Home32,
                        onActionClick = {
                            onIntent(MainIntent.GoToSettingsIntent)
                        },
                        onNavigationClick = {
                            onIntent(MainIntent.GoToHomeIntent)
                        }
                    )
                }

                HorizontalAnimatedDivider(
                    modifier = Modifier.fillMaxWidth(),
                    isVisible = pagerState.currentPage != 0
                )

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
                        isEmpty = state.rooms?.isEmpty() == true,
                        onRefresh = {
                            onIntent(MainIntent.RefreshIntent)
                        },

                        ) {
                        Row(
                            modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            VerticalCompressingPager(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        horizontal = Theme.spacing.space16,
                                        vertical = Theme.spacing.space16,
                                    )
                                    .clipToBounds(),
                                pagerState = pagerState,
                                items = state.rooms ?: emptyList()
                            ) { item ->
                                RoomCard(
                                    modifier = Modifier,
//                                        .fillMaxWidth()
//                                        .aspectRatio(0.5f),
//                                        .fillMaxWidth()
//                                        .aspectRatio(0.5f),
                                    background = item.color?.toColor() ?: Color.Cyan,
                                    title = item.name.value,
                                    isEmpty = item.selectedDevices.isEmpty(),
                                    onAction = {
                                        onIntent(MainIntent.GoToRoomIntent(item))
                                    },
                                    isSelected = true
                                ) {
                                    items(item.selectedDevices) {
                                        DeviceCard(
                                            title = it.name.value,
                                            background = Theme.color.accent2,
                                            onClick = {
                                                onIntent(MainIntent.GoToDeviceIntent(it))
                                            }
                                        ) {
                                            when (it.generalType()) {
                                                GeneralDeviceType.Light -> PushButton(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .aspectRatio(1f),
                                                    icon = LogIn24,
                                                    isSelected = (it.attribute as? OnOffAttributeEntity)?.isOn
                                                        ?: false,
                                                    onClick = {}
                                                )

                                                GeneralDeviceType.Switch -> PushButton(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .aspectRatio(1f),
                                                    icon = LogIn24,
                                                    isSelected = (it.attribute as? OnOffAttributeEntity)?.isOn
                                                        ?: false,
                                                    onClick = {}
                                                )

                                                GeneralDeviceType.Temperature -> TemperatureDevice(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .aspectRatio(1f),
                                                    temperature = (it.attribute as? TemperatureAttributeEntity)?.temperature
                                                        ?: "--"
                                                )

                                                GeneralDeviceType.Contact -> ContactDevice(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .aspectRatio(1f),
                                                    state = it.customType?.toDeviceType(),
                                                    cancelled = (it.attribute as? ContactAttributeEntity)?.isOpen
                                                        ?: false
                                                )

                                                GeneralDeviceType.WindowCovering -> WindowCoveringDevice(
                                                    modifier = Modifier
                                                        .fillMaxHeight()
                                                        .aspectRatio(1f),
                                                )


                                                else -> Unit

                                            }


                                        }
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
