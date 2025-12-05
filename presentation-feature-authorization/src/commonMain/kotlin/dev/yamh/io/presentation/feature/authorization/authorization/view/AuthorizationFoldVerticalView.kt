package dev.yamh.io.presentation.feature.authorization.authorization.view

import StackedSnackbarAnimation
import StackedSnackbarHost
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.LoadingCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldVerticalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.MaxSideDetector
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.MouseScrollDown32
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.AuthorizationBottomActionBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.card.OnboardingCard
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationIntent
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationState
import dev.yamh.presentation.core.styling.core.Theme
import rememberStackedSnackbarHostState

@Composable
public fun AuthorizationFoldVerticalView(
    state: AuthorizationState,
    onIntent: (AuthorizationIntent) -> Unit
) {
    val localisation = LocalLocalisation.current

    val stackedSnackbarHostState =
        rememberStackedSnackbarHostState(
            animation = StackedSnackbarAnimation.Slide,
            maxStack = 5,
        )

    LaunchedEffect(state.error) {
        state.error?.let {
            stackedSnackbarHostState.showSnackbar(
                title = localisation.general.error,
                description = state.error ?: localisation.general.error,
            )
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
            FoldVerticalLayout(
                top = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedItem(
                            modifier = Modifier.weight(1f),
                            index = 0,
                            enter = slideInVertically(
                                tween(
                                    durationMillis = 250,
                                )
                            ) { fullHeight -> -fullHeight }
                        ) {
                            MaxSideDetector(
                                modifier = Modifier.weight(1f),
                                byHeight = {
                                    Crossfade(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .aspectRatio(1f),
                                        targetState = state.isLoading
                                    ) { isLoading ->
                                        if (isLoading) {
                                            LoadingCard(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = Theme.spacing.space16),
                                                background = Theme.color.primary0,
                                            )
                                        } else {
                                            OnboardingCard(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = Theme.spacing.space16),
                                                background = Theme.color.secondary2,
                                                title = localisation.authorization.title,
                                                description = localisation.authorization.description,
                                            )
                                        }
                                    }
                                },
                                byWidth = {
                                    Crossfade(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .aspectRatio(1f),
                                        targetState = state.isLoading
                                    ) { isLoading ->
                                        if (isLoading) {
                                            LoadingCard(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = Theme.spacing.space16),
                                                background = Theme.color.primary0,
                                            )
                                        } else {
                                            OnboardingCard(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(horizontal = Theme.spacing.space16),
                                                background = Theme.color.secondary2,
                                                title = localisation.authorization.title,
                                                description = localisation.authorization.description,
                                            )
                                        }
                                    }
                                }
                            )
                        }
                    }
                },
                bottom = {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        AnimatedItem(
                            modifier = Modifier,
                            index = 1,
                            enter = slideInVertically(
                                tween(
                                    durationMillis = 250,
                                )
                            ) { fullHeight -> +fullHeight }
                        ) {
                            AuthorizationBottomActionBar(
                                modifier = Modifier,
                            ) {
                                onIntent(AuthorizationIntent.OnAuthorizeIntent)
                            }
                        }
                    }
                }
            )
        }
    }
}

