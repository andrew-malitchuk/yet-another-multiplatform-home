package dev.yamh.io.presentation.feature.authorization.authorization.view

import StackedSnackbarAnimation
import StackedSnackbarHost
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.LoadingCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldHorizontalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldVerticalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.MaxSideDetector
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.AuthorizationBottomActionBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.card.OnboardingCard
import dev.yamh.io.presentation.core.ui.source.kit.molecule.grid.Grid
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationIntent
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationState
import dev.yamh.presentation.core.styling.core.Theme
import rememberStackedSnackbarHostState

@Composable
public fun AuthorizationExpandedView(
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
        ) {
            FoldHorizontalLayout(
                start = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        MaxSideDetector(
                            modifier = Modifier
                                .fillMaxSize(),
                            byHeight = {
                                Crossfade(targetState = state.isLoading) { isLoading ->
                                    if (isLoading) {
                                        LoadingCard(
                                            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                                                .padding(horizontal = Theme.spacing.space16),
                                            background = Theme.color.primary0,
                                        )
                                    } else {
                                        OnboardingCard(
                                            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
                                                .padding(horizontal = Theme.spacing.space16),
                                            background = Theme.color.secondary2,
                                            title = localisation.authorization.title,
                                            description = localisation.authorization.description,
                                        )
                                    }
                                }
                            },
                            byWidth = {
                                Crossfade(targetState = state.isLoading) { isLoading ->
                                    if (isLoading) {
                                        LoadingCard(
                                            modifier = Modifier.fillMaxHeight().aspectRatio(1f)
                                                .padding(horizontal = Theme.spacing.space16),
                                            background = Theme.color.primary0,
                                        )
                                    } else {
                                        OnboardingCard(
                                            modifier = Modifier.fillMaxHeight().aspectRatio(1f)
                                                .padding(horizontal = Theme.spacing.space16),
                                            background = Theme.color.secondary2,
                                            title = localisation.authorization.title,
                                            description = localisation.authorization.description,
                                        )
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                },
                end = {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Spacer(modifier = Modifier.weight(1f))
                        AuthorizationBottomActionBar(
                            modifier = Modifier,
                        ) {
                            onIntent(AuthorizationIntent.OnAuthorizeIntent)
                        }
                    }
                }
            )
        }
    }
}

