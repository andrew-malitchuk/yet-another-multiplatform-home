package dev.yamh.io.presentation.feature.device.source.contact.view

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
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.MaxSideDetector
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Droplet32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionAdvancedBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.device.ContactDevice
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactIntent
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactState
import dev.yamh.io.presentation.feature.device.source.settings.view.toDeviceType
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherIntent
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun ContactCompactView(
    state: ContactState,
    onIntent: (ContactIntent) -> Unit
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
                                    ContactIntent.GoToSettingsIntent(
                                        device
                                    )
                                )
                            }
                        },
                        onNavigationClick = {
                            onIntent(ContactIntent.OnBackClickIntent)
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
                    MaxSideDetector(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(Theme.spacing.space16),
                        byHeight = {
                            ContactDevice(
                                modifier = Modifier.fillMaxWidth()
                                    .aspectRatio(1f),
                                state = state.customType?.toDeviceType(),
                                cancelled = false
                            )
                        },
                        byWidth = {
                            ContactDevice(
                                modifier = Modifier.fillMaxHeight()
                                    .aspectRatio(1f),
                                state = state.customType?.toDeviceType(),
                                cancelled = false
                            )
                        }
                    )
                }
            }
        }
    }
}

