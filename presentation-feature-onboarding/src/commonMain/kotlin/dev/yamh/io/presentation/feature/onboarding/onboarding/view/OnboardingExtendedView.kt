package dev.yamh.io.presentation.feature.onboarding.onboarding.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldHorizontalLayout
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.pager.CircleAnimatedRevealPager
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.OnboardingBottomActionBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.card.OnboardingCard
import dev.yamh.io.presentation.core.ui.source.kit.molecule.grid.Grid
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingIntent
import dev.yamh.presentation.core.styling.core.Theme


@Composable
public fun OnboardingExpandedView(
    onIntent: (OnboardingIntent) -> Unit
) {
    val localisation = LocalLocalisation.current

    var page by remember { mutableIntStateOf(0) }

    val colors = arrayOf(
        Color(0xFF7D5DFF),
        Color(0xFF62CC79),
        Color(0xFFEF8869),
        Color(0xFFEA6DF7),
    )

    val content = arrayOf(
        localisation.onboarding.page1Title to localisation.onboarding.page1Description,
        localisation.onboarding.page2Title to localisation.onboarding.page2Description,
        localisation.onboarding.page3Title to localisation.onboarding.page3Description,
        localisation.onboarding.page4Title to localisation.onboarding.page4Description,
    )

    SafeContainer {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.primary0)
                .safeDrawingPadding()
                .padding(horizontal = Theme.spacing.space8)
        ) {
            FoldHorizontalLayout(
                start = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(Theme.spacing.space8),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = localisation.onboarding.welcome,
                            style = Theme.typography.display,
                            color = Theme.color.primary1,
                            maxLines = 2,
                            minLines = 2,
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Grid(
                            modifier = Modifier
                                .fillMaxHeight()
                                .aspectRatio(1f),
                            page = page
                        )
                    }
                },
                end = {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(Theme.spacing.space8),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        androidx.compose.animation.AnimatedContent(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            targetState = page
                        ) { target ->
                            OnboardingCard(
                                modifier = Modifier
                                    .weight(1f)
                                    .aspectRatio(1f),
                                background = colors[target],
                                title = content[target].first,
                                description = content[target].second,
                            )
                        }
                        Spacer(modifier = Modifier.height(Theme.spacing.space16))
                        OnboardingBottomActionBar(
                            modifier = Modifier,
                            done = page == 3,
                            onClick = {
                                if (page < 3) {
                                    page++
                                } else {
                                    onIntent(OnboardingIntent.OnOnboardingDoneIntent)
                                }
                            }
                        )
                    }
                }
            )
        }
    }
}