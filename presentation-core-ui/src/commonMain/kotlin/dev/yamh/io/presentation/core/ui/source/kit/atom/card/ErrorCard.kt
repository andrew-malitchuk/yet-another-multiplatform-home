package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.core.ext.animatedBorder
import dev.yamh.io.presentation.core.ui.core.ext.glitchEffect
import dev.yamh.presentation.core.styling.core.Theme
import kotlin.random.Random

@Composable
public fun ErrorCard(
    modifier: Modifier,
    background: Color,
) {

    var key by remember { mutableStateOf(1) }
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
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        key = Random.nextInt()
                    }
                )
            }
            .padding(Theme.spacing.space24),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = "#error",
                style = Theme.typography.title,
                color = Theme.color.accent2,
                maxLines = 1,
                minLines = 1,
                modifier = Modifier
                    .glitchEffect(
                        key,
                        remember { listOf(Color.Cyan, Color.Red, Color.Green) }
                    )
            )


        }
    )
}