package dev.yamh.io.presentation.feature.device.source.temperature.view

import StackedSnackbarHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.jordond.connectivity.Connectivity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldHorizontalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldVerticalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.MaxSideDetector
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionAdvancedBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.TemperatureDevice
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureIntent
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun TemperatureExpandedView(
    state: TemperatureState,
    onIntent: (TemperatureIntent) -> Unit
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
                    TopActionAdvancedBar(
                        text = state.device?.name?.value ?: "NI",
                        action = Settings32,
                        onActionClick = {
                            state.device?.let { device ->
                                onIntent(
                                    TemperatureIntent.GoToSettingsIntent(
                                        device
                                    )
                                )
                            }
                        },
                        onNavigationClick = {
                            onIntent(TemperatureIntent.OnBackClickIntent)
                        }
                    )
                }
                Spacer(modifier = Modifier.height(Theme.spacing.space16))
                AnimatedItem(
                    index = 1,
                    enter = slideInVertically(
                        tween(
                            durationMillis = 250,
                        )
                    ) { fullHeight -> +fullHeight }
                ) {
                    FoldHorizontalLayout(
                        start = {
                            MaxSideDetector(
                                modifier = Modifier
                                    .fillMaxSize(),
                                byHeight = {
                                    TemperatureDevice(
                                        modifier = Modifier
                                            .padding(bottom = Theme.spacing.space56)
                                            .fillMaxWidth()
                                            .aspectRatio(1f),
                                        temperature = state.temperature ?: "--"
                                    )
                                },
                                byWidth = {
                                    TemperatureDevice(
                                        modifier = Modifier
                                            .padding(bottom = Theme.spacing.space56)
                                            .fillMaxHeight()
                                            .aspectRatio(1f),
                                        temperature = state.temperature ?: "--"
                                    )
                                }
                            )
                        },
                        end = {}
                    )
                }
            }
        }
    }
}