package dev.yamh.io.presentation.feature.room.room.view

import StackedSnackbarHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.jordond.connectivity.Connectivity
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.GeneralDeviceType
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.PushButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.ContentSwitcher
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopSearchBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.column.ScaledEdgedLazyColumn
import dev.yamh.io.presentation.core.ui.source.kit.molecule.column.itemsScaled
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.ContactDevice
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.NoDataDevice
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.TemperatureDevice
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.WindowCoveringDevice
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.core.ui.source.kit.organism.card.DeviceCard
import dev.yamh.io.presentation.feature.room.core.util.toDeviceType
import dev.yamh.io.presentation.feature.room.room.mvi.RoomIntent
import dev.yamh.io.presentation.feature.room.room.mvi.RoomState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun RoomCompactView(
    onIntent: (RoomIntent) -> Unit,
    state: RoomState
) {

    @Composable
    fun GeneralList(input: List<DeviceEntity>) {
        val screenMinItemWidth = 200.dp

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
        ) {

            val columns = (maxWidth / screenMinItemWidth).toInt().coerceAtLeast(1)


            ScaledEdgedLazyColumn(columns = columns) { listState, scaling ->
                itemsScaled(
                    state = listState,
                    items = input,          // your list
                    params = scaling
                ) {
                    DeviceCard(
                        title = it.name.value,
                        background = Theme.color.accent2,
                        onClick = {
                            onIntent(RoomIntent.GoToDeviceIntent(it))
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

                            else -> NoDataDevice(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f),
                            )
                        }
                    }
                }
            }
        }
    }

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
                    TopSearchBar(
                        text = state.room?.name?.value ?: "NI",
                        onNavClick = { onIntent(RoomIntent.OnBackClickIntent) }
                    ) {
                        onIntent(RoomIntent.SearchIntent(it))
                    }
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
                        isEmpty = state.devices?.isEmpty() == true || state.searchResult?.isEmpty() == true,
                        onRefresh = {
                            onIntent(RoomIntent.RefreshIntent)
                        },

                        ) {

                        when {
                            state.searchResult?.isNotEmpty() == true -> {
                                GeneralList(input = state.searchResult)
                            }

                            else -> {
                                GeneralList(input = state.devices ?: emptyList())
                            }

                        }
                    }


                }
            }
        }

    }
}



