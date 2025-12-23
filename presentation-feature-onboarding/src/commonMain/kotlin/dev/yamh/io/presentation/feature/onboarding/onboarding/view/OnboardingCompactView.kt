package dev.yamh.io.presentation.feature.onboarding.onboarding.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.MaxSideDetector
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.OnboardingBottomActionBar
import dev.yamh.io.presentation.core.ui.source.kit.molecule.grid.Grid
import dev.yamh.io.presentation.feature.onboarding.onboarding.mvi.OnboardingIntent
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun OnboardingCompactView(
    onIntent: (OnboardingIntent) -> Unit
) {
    val localisation = LocalLocalisation.current

    var page by remember { mutableIntStateOf(0) }

    SafeContainer {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.color.primary0)
                .safeDrawingPadding()
                .padding(horizontal = Theme.spacing.space8)
        ) {
            Text(
                text = localisation.onboarding.welcome,
                style = Theme.typography.display,
                color = Theme.color.primary1,
                maxLines = 2,
                minLines = 2,
            )
            MaxSideDetector(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                byWidth = {
                    Grid(
                        modifier = Modifier.fillMaxHeight().aspectRatio(1f),
                        page = page
                    )
                },
                byHeight = {
                    Grid(
                        modifier = Modifier.fillMaxWidth().aspectRatio(1f),
                        page = page
                    )
                }
            )
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
}

