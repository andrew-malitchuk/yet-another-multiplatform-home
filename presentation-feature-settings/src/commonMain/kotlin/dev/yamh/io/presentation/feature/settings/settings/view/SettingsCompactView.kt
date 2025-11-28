package dev.yamh.io.presentation.feature.settings.settings.view

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.dp
import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.PushButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.toggle.Toggle
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.TitledCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.En32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Github32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Linkedin32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Moon32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Sun32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Ua32
import dev.yamh.io.presentation.core.ui.source.kit.atom.indicator.PagerIndicatorOrientation
import dev.yamh.io.presentation.core.ui.source.kit.atom.indicator.PagerWormIndicator
import dev.yamh.io.presentation.core.ui.source.kit.atom.pager.VerticalCompressingPager
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.stack.StackVertical
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.CustomDeviceType
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopNavigationBar
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.core.ui.source.kit.organism.card.DeviceDetailsCard
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsIntent
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsState
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun SettingsCompactView(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit
) {
    val localisation = LocalLocalisation.current

    val sections: MutableList<@Composable () -> Unit> = mutableListOf(
        {
            TitledCard(
                modifier = Modifier,
                background = Theme.color.accent1,
                title = localisation.settings.theme,
                isSelected = true,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(SquircleShape(32.dp))
                        .background(Theme.color.accent2)
                        .border(4.dp, Theme.color.primary0, SquircleShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier,
                    ) {
                        PushButton(
                            icon = Sun32,
                            isSelected = state.theme == ThemeEntity.LIGHT,
                        ) {
                            onIntent(
                                SettingsIntent.OnThemeChangeIntent(
                                    ThemeEntity.LIGHT
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(Theme.spacing.space16))
                        PushButton(
                            icon = Moon32,
                            isSelected = state.theme == ThemeEntity.DARK,
                        ) {
                            onIntent(
                                SettingsIntent.OnThemeChangeIntent(
                                    ThemeEntity.DARK
                                )
                            )
                        }
                    }
                }
            }
        },
        {
            TitledCard(
                modifier = Modifier,
                background = Theme.color.accent2,
                title = localisation.settings.type,
                isSelected = true,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(SquircleShape(32.dp))
                        .background(Theme.color.secondary1)
                        .border(4.dp, Theme.color.primary0, SquircleShape(32.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        PushButton(
                            icon = Ua32,
                            isSelected = state.language == LanguageEntity.UKRAINIAN,
                        ) {
                            onIntent(
                                SettingsIntent.OnLanguageChangeIntent(
                                    LanguageEntity.UKRAINIAN
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(Theme.spacing.space16))
                        PushButton(
                            icon = En32,
                            isSelected = state.language == LanguageEntity.ENGLISH,
                        ) {
                            onIntent(
                                SettingsIntent.OnLanguageChangeIntent(
                                    LanguageEntity.ENGLISH
                                )
                            )
                        }
                    }
                }
            }
        },
        {
            TitledCard(
                modifier = Modifier,
                background = Theme.color.secondary0,
                title = localisation.settings.about,
                isSelected = true,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(SquircleShape(32.dp))
                        .background(Theme.color.accent2)
                        .border(4.dp, Theme.color.primary0, SquircleShape(32.dp))
                        .padding(Theme.spacing.space16),
                ) {
                    Text(
                        text = localisation.settings.aboutDescription,
                        style = Theme.typography.body,
                        color = Theme.color.primary0,
                        maxLines = 2,
                        minLines = 2,
                    )
                    Spacer(modifier = Modifier.height(Theme.spacing.space8))
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {

                        TertiaryIconButton(
                            icon = Github32,
                        ) {
                            onIntent(
                                SettingsIntent.OnGoToGithubIntent
                            )
                        }
                        Spacer(modifier = Modifier.width(Theme.spacing.space16))
                        TertiaryIconButton(
                            icon = Linkedin32,
                        ) {
                            onIntent(
                                SettingsIntent.OnGoToLinkedInIntent
                            )
                        }
                    }
                }
            }
        })
    val pagerState = rememberPagerState { sections.size }

    SafeContainer {
        AnimationSequenceHost {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                TopNavigationBar {
                    onIntent(SettingsIntent.OnBackClickIntent)
                }
                AnimatedItem(
                    index = 0,
                    enter = slideInVertically(
                        tween(
                            durationMillis = 250,
                        )
                    ) { fullHeight -> +fullHeight }
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        VerticalCompressingPager(
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    horizontal = Theme.spacing.space16,
                                    vertical = Theme.spacing.space16,
                                )
                                .clipToBounds(),
                            pagerState = pagerState,
                        ) { index ->
                            sections[index]()
                        }
                        PagerWormIndicator(
                            modifier = Modifier,
                            pagerState = pagerState,
                            activeDotColor = Theme.color.accent1,
                            dotColor = Theme.color.primary2,
                            orientation = PagerIndicatorOrientation.Vertical
                        )
                    }
                }
            }
        }
    }
}

