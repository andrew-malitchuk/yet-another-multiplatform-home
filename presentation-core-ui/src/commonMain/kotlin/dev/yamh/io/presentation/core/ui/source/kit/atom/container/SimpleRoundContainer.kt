package dev.yamh.io.presentation.core.ui.source.kit.atom.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color


@Composable
public fun SimpleRoundContainer(
    modifier: Modifier,
    background: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier.clip(CircleShape).background(background),
        contentAlignment = Alignment.Center,
        content = content
    )
}