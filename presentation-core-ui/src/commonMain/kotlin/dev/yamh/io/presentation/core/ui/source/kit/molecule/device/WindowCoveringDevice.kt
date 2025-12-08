package dev.yamh.io.presentation.core.ui.source.kit.molecule.device

import ArrowDown32
import ArrowUp32
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.primary.PrimaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SimpleRoundContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.text.AutoSizeText
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun WindowCoveringDevice(
    modifier: Modifier = Modifier,
) {
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

        }
        Spacer(Modifier.height(4.dp))
        PrimaryIconButton(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f),
            icon = ArrowDown32,
        ) {

        }
    }
}