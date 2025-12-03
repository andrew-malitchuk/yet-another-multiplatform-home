package dev.yamh.io.presentation.feature.device.source.settings.view

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.PushButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.toggle.Toggle
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.TitledCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Lightning32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Sun32
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.stack.StackVertical
import dev.yamh.io.presentation.core.ui.source.kit.atom.stack.StackVerticalAnimated
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.CustomDeviceType
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.TopNavigationBar
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimatedItem
import dev.yamh.io.presentation.core.ui.source.kit.organism.animatedsequence.AnimationSequenceHost
import dev.yamh.io.presentation.core.ui.source.kit.organism.card.DeviceDetailsCard
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsIntent
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsState
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun SettingsFoldVerticalView(
    state: SettingsState,
    onIntent: (SettingsIntent) -> Unit
) {
    val localisation = LocalLocalisation.current

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
                    StackVerticalAnimated(
                        modifier = Modifier.padding(top = Theme.spacing.space8),
                        stackedState = false,
                    ) {
                        item {
                            DeviceDetailsCard(
                                modifier = Modifier,
                                id = state.device?.id,
                                name = state.device?.name,
                                homeId = state.device?.homeId,
                                roomId = state.device?.roomId,
                            )
                        }
                        item {
                            TitledCard(
                                modifier = Modifier,
                                background = Theme.color.secondary0,
                                title = localisation.settings.customize,
                                isSelected = true,
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(SquircleShape(32.dp))
                                        .background(Theme.color.accent2)
                                        .border(4.dp, Theme.color.primary0, SquircleShape(32.dp))
                                        .padding(Theme.spacing.space16),
                                ) {
                                    Text(
                                        text = localisation.settings.selected,
                                        style = Theme.typography.body,
                                        color = Theme.color.primary0,
                                        maxLines = 2,
                                        minLines = 2,
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Toggle(
                                        modifier = Modifier,
                                        checked = state.isDeviceSelected,
                                        onCheckedChange = {
                                            onIntent(SettingsIntent.OnSettingsChangedIntent(it))
                                        }
                                    )
                                }
                            }
                        }
                        if (state.device?.type?.contains(dev.yamh.domain.core.source.model.device.type.DeviceType.Contact) == true) {

                            item {
                                TitledCard(
                                    modifier = Modifier,
                                    background = Theme.color.accent1,
                                    title = localisation.settings.type,
                                    isSelected = true,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clip(SquircleShape(32.dp))
                                            .background(Theme.color.accent2)
                                            .border(
                                                4.dp,
                                                Theme.color.primary0,
                                                SquircleShape(32.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        CustomDeviceType(
                                            modifier = Modifier.align(Alignment.Center),
                                            state = state.device?.customType?.toDeviceType()
                                                ?: DeviceType.Water,
                                        ) {
                                            onIntent(
                                                SettingsIntent.OnDeviceCustomTypeChangedIntent(
                                                    it.toDeviceCustomType()
                                                        ?: DeviceCustomType.Water
                                                )
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }


}

