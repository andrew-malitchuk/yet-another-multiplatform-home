package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun SimpleCard(
    modifier: Modifier,
    background: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(SquircleShape(32.dp))
            .background(background)
            .padding(Theme.spacing.space24),
        content = content
    )
}