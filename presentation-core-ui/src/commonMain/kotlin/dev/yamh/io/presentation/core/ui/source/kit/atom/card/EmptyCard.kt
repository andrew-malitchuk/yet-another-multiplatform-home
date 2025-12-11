package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.core.ext.animatedBorder
import dev.yamh.io.presentation.core.ui.core.ext.glitchEffect
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Slash32
import dev.yamh.io.presentation.core.ui.source.kit.atom.text.AutoSizeText
import dev.yamh.presentation.core.styling.core.Theme
import kotlin.random.Random

@Composable
public fun EmptyCard(
    modifier: Modifier,
    background: Color,
) {

    val localisation = LocalLocalisation.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(SquircleShape(32.dp))
            .background(background)
            .border(
                width = 4.dp,
                color = Theme.color.primary1,
                shape = SquircleShape(32.dp)
            )
            .padding(Theme.spacing.space24),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(32.dp),
                    imageVector = Slash32,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Theme.color.primary1)
                )
                AutoSizeText(
                    modifier = Modifier,
                    text = localisation.general.empty,
                    style = Theme.typography.title,
                    color = Theme.color.primary1,
                    maxLines = 1,
                    minLines = 1,
                )
            }


        }
    )
}