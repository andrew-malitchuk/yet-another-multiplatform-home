package dev.yamh.io.presentation.core.ui.source.kit.molecule.grid

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.LineHeightStyle
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Morph
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.text.AutoSizeText
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun Grid(
    modifier: Modifier = Modifier,
    page: Int,
) {
    Box(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = page,
                transitionSpec = {
                    slideInVertically { fullHeight ->
                        // Enter from bottom
                        fullHeight
                    } + fadeIn() togetherWith
                            slideOutVertically { fullHeight ->
                                // Exit toward top
                                -fullHeight
                            } + fadeOut()
                },
                label = "pageAnimation0"
            ) { targetPage ->
                when (targetPage) {
                    0 -> FirstRow(modifier = Modifier.weight(1f))
                    else -> StubRow(modifier = Modifier.weight(1f))
                }
            }
            Spacer(
                modifier = Modifier.height(Theme.spacing.space8)
            )
            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = page,
                transitionSpec = {
                    slideInVertically { fullHeight ->
                        // Enter from bottom
                        fullHeight
                    } + fadeIn() togetherWith
                            slideOutVertically { fullHeight ->
                                // Exit toward top
                                -fullHeight
                            } + fadeOut()
                },
                label = "pageAnimation1"
            ) { targetPage ->
                when (targetPage) {
                    1 -> SecondRow(modifier = Modifier.weight(1f))
                    else -> StubRow(modifier = Modifier.weight(1f))
                }
            }
            Spacer(
                modifier = Modifier.height(Theme.spacing.space8)
            )
            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = page,
                transitionSpec = {
                    slideInVertically { fullHeight ->
                        // Enter from bottom
                        fullHeight
                    } + fadeIn() togetherWith
                            slideOutVertically { fullHeight ->
                                // Exit toward top
                                -fullHeight
                            } + fadeOut()
                },
                label = "pageAnimation2"
            ) { targetPage ->
                when (targetPage) {
                    2 -> ThirdRow(modifier = Modifier.weight(1f))
                    else -> StubRow(modifier = Modifier.weight(1f))
                }
            }
            Spacer(
                modifier = Modifier.height(Theme.spacing.space8)
            )
            AnimatedContent(
                modifier = Modifier.weight(1f),
                targetState = page,
                transitionSpec = {
                    slideInVertically { fullHeight ->
                        // Enter from bottom
                        fullHeight
                    } + fadeIn() togetherWith
                            slideOutVertically { fullHeight ->
                                // Exit toward top
                                -fullHeight
                            } + fadeOut()
                },
                label = "pageAnimation3"
            ) { targetPage ->
                when (targetPage) {
                    3 -> FourthRow(modifier = Modifier.weight(1f))
                    else -> StubRow(modifier = Modifier.weight(1f))
                }
            }
        }

    }
}

@Composable
private fun FirstRow(modifier: Modifier = Modifier) {
    val localisation = LocalLocalisation.current

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.weight(1f)
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Morph),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Theme.color.primary1),
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )
            AutoSizeText(
                text = "1",
                style = Theme.typography.headline,
                color = Theme.color.primary0,
            )
        }
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
                .clip(SquircleShape(Theme.size.size32))
                .background(Theme.color.accent1)
                .padding(Theme.spacing.space16),
            contentAlignment = Alignment.Center
        ) {
            AutoSizeText(
                text = localisation.onboarding.page1Title,
                style = Theme.typography.title,
                color = Theme.color.primary0,
            )
        }
    }
}

@Composable
private fun SecondRow(modifier: Modifier = Modifier) {
    val localisation = LocalLocalisation.current

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.weight(1f)
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Morph),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Theme.color.primary1),
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )
            AutoSizeText(
                text = "2",
                style = Theme.typography.headline,
                color = Theme.color.primary0,
            )
        }
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
                .clip(SquircleShape(Theme.size.size32))
                .background(Theme.color.accent1)
                .padding(Theme.spacing.space16),
            contentAlignment = Alignment.Center
        ) {
            AutoSizeText(
                text = localisation.onboarding.page2Title,
                style = Theme.typography.title,
                color = Theme.color.primary0,
            )
        }
    }
}

@Composable
private fun ThirdRow(modifier: Modifier = Modifier) {
    val localisation = LocalLocalisation.current

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
                .clip(SquircleShape(Theme.size.size32))
                .background(Theme.color.accent1)
                .padding(Theme.spacing.space16),
            contentAlignment = Alignment.Center
        ) {
            AutoSizeText(
                text = localisation.onboarding.page3Title,
                style = Theme.typography.title,
                color = Theme.color.primary0,
            )
        }
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier.weight(1f)
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Morph),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Theme.color.primary1),
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )
            AutoSizeText(
                text = "3",
                style = Theme.typography.headline,
                color = Theme.color.primary0,
            )
        }
    }
}

@Composable
private fun FourthRow(modifier: Modifier = Modifier) {
    val localisation = LocalLocalisation.current

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.weight(1f)
                .aspectRatio(1f), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Morph),
                contentDescription = null,
                colorFilter = ColorFilter.tint(Theme.color.primary1),
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillBounds
            )
            AutoSizeText(
                text = "4",
                style = Theme.typography.headline,
                color = Theme.color.primary0,
            )
        }
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier
                .weight(3f)
                .fillMaxHeight()
                .clip(SquircleShape(Theme.size.size32))
                .background(Theme.color.accent1)
                .padding(Theme.spacing.space16),
            contentAlignment = Alignment.Center
        ) {
            AutoSizeText(
                text = localisation.onboarding.page4Title,
                style = Theme.typography.title,
                color = Theme.color.primary0,
            )
        }
    }
}

@Composable
private fun StubRow(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Theme.color.secondary0)
                .padding(Theme.spacing.space8),
            content = {}
        )
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Theme.color.secondary0)
                .padding(Theme.spacing.space8),
            content = {}
        )
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Theme.color.secondary0)
                .padding(Theme.spacing.space8),
            content = {}
        )
        Spacer(
            modifier = Modifier.width(Theme.spacing.space8)
        )
        Box(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Theme.color.secondary0)
                .padding(Theme.spacing.space8),
            content = {}
        )
    }
}