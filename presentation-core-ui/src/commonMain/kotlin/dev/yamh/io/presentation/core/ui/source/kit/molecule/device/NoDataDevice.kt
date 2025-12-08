package dev.yamh.io.presentation.core.ui.source.kit.molecule.device

import HelpCircle32
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun NoDataDevice(
    modifier: Modifier = Modifier,
) {

    Image(
        modifier = modifier.fillMaxSize(),
                imageVector = HelpCircle32,
        contentDescription = null,
        colorFilter = ColorFilter.tint(Theme.color.primary1)
    )

}