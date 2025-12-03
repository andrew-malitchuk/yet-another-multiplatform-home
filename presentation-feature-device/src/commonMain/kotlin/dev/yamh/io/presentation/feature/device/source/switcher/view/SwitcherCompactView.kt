package dev.yamh.io.presentation.feature.device.source.switcher.view

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
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.PushButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.PushButtonLoadingCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.MaxSideDetector
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Settings32
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopActionAdvancedBar
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherIntent
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherState
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.launch
import rememberStackedSnackbarHostState

@Composable
public fun SwitcherCompactView(
    state: SwitcherState,
    onIntent: (SwitcherIntent) -> Unit
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
                                    SwitcherIntent.GoToSettingsIntent(
                                        device
                                    )
                                )
                            }
                        },
                        onNavigationClick = {
                            onIntent(SwitcherIntent.OnBackClickIntent)
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
                            when (state.isLoading) {
                                true -> PushButtonLoadingCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f),
                                    isSelected = state.onOff,
                                    background = Theme.color.primary0
                                )

                                false -> PushButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(1f),
                                    icon = LogIn24,
                                    isSelected = state.onOff,
                                ) {
                                    onIntent(
                                        SwitcherIntent.ChangeAttribute(
                                            attribute = OnOffAttributeEntity(
                                                !state.onOff
                                            )
                                        )
                                    )
                                }
                            }
                        },
                        byWidth = {
                            when (state.isLoading) {
                                true -> PushButtonLoadingCard(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .aspectRatio(1f),
                                    isSelected = state.onOff,
                                    background = Theme.color.primary0
                                )

                                false -> PushButton(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .aspectRatio(1f),
                                    icon = LogIn24,
                                    isSelected = state.onOff,
                                ) {
                                    onIntent(
                                        SwitcherIntent.ChangeAttribute(
                                            attribute = OnOffAttributeEntity(
                                                !state.onOff
                                            )
                                        )
                                    )
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(Theme.spacing.space16))
//            FilledSlider(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(4f)
//                    .align(Alignment.CenterHorizontally),
//                sliderShape = SquircleShape(16.dp),
//                sliderOrientation = SliderOrientation.Horizontal,
//                currentValue = currentValue,
//                valueRange = 0f..254f,
//                setCurrentValue = {
//                    currentValue = it
//                }
//            )


                }
            }
        }
    }
}

