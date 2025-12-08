package dev.yamh.io.presentation.core.ui.source.kit.molecule.device

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SimpleRoundContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.text.AutoSizeText
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun TemperatureDevice(
    modifier: Modifier = Modifier,
    temperature: String
) {
    SimpleRoundContainer(
        modifier = modifier,
        background = Theme.color.primary1,
    ) {
        AutoSizeText(
            text = "${temperature}°",
            style = Theme.typography.title,
        )
    }
}