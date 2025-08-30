package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.core.ext.animatedBorder
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun LoadingCard(
    modifier: Modifier,
    background: Color,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(RoundedCornerShape(32.dp))
            .background(background)
            .animatedBorder(
                borderColors = listOf(
                    Theme.color.accent0,
                    Theme.color.accent1,
                    Theme.color.accent2
                ),
                backgroundColor = background,
                shape = RoundedCornerShape(32.dp),
                borderWidth = 4.dp
            )
            .padding(Theme.spacing.space24),
        content = {}
    )
}