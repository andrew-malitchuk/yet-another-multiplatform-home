package dev.yamh.io.presentation.feature.device.source.window.view

import ArrowDown32
import ArrowUp32
import StackedSnackbarAnimation
import StackedSnackbarHost
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.jordond.connectivity.Connectivity
import dev.yamh.domain.core.source.model.device.attribute.WindowCoveringEntity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.primary.PrimaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldVerticalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionAdvancedBar
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowIntent
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun WindowFoldVerticalView(
    state: WindowState,
    onIntent: (WindowIntent) -> Unit
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
                                    WindowIntent.GoToSettingsIntent(
                                        device
                                    )
                                )
                            }
                        },
                        onNavigationClick = {
                            onIntent(WindowIntent.OnBackClickIntent)
                        }
                    )
                }
                AnimatedItem(
                    index = 1,
                    enter = slideInVertically(
                        tween(
                            durationMillis = 250,
                        )
                    ) { fullHeight -> +fullHeight }
                ) {
                    FoldVerticalLayout(
                        top = {
                            Column(
                                modifier = Modifier.fillMaxSize()
                                    .padding(Theme.spacing.space16),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                PrimaryIconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f),
                                    icon = ArrowUp32,
                                ) {
                                    onIntent(
                                        WindowIntent.ChangeAttribute(
                                            attribute = WindowCoveringEntity(
                                                isOpen = false
                                            )
                                        )
                                    )
                                }
                                Spacer(Modifier.height(Theme.spacing.space8))
                                PrimaryIconButton(
                                    modifier = Modifier
                                        .weight(1f)
                                        .aspectRatio(1f),
                                    icon = ArrowDown32,
                                ) {
                                    onIntent(
                                        WindowIntent.ChangeAttribute(
                                            attribute = WindowCoveringEntity(
                                                isOpen = true
                                            )
                                        )
                                    )
                                }
                            }
                        },
                        bottom = {
                            Spacer(modifier = Modifier.height(Theme.spacing.space16))
                        }
                    )
                }
            }
        }
    }
}

