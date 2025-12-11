package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.core.ext.noRippleClickable
import dev.yamh.io.presentation.core.ui.core.ext.noRippleLongClickable
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun SelectableCard(
    modifier: Modifier,
    background: Color,
    title: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {

    val animatedColor = animateColorAsState(
        targetValue = if (isSelected) {
            Theme.color.primary1
        } else {
            Theme.color.primary0
        },
        label = "animatedColor"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .clip(SquircleShape(32.dp))
            .background(background)
            .border(
                width = 4.dp,
                color = animatedColor.value,
                shape = SquircleShape(32.dp)
            )
            .padding(Theme.spacing.space24),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text(
                text = title,
                style = Theme.typography.title,
                color = Theme.color.primary0,
                maxLines = 2,
                minLines = 2,
            )
            AnimatedVisibility(
                visible = isSelected,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    content = content
                )
            }
        }
    }
}