package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.carousel.vertical.Arrangement
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Plus32
import dev.yamh.presentation.core.styling.core.Theme


@Composable
public fun TitledCard(
    modifier: Modifier,
    background: Color,
    title: String,
    isSelected: Boolean = false,
    action: ImageVector? = null,
    onAction: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.5f)
            .clip(RoundedCornerShape(32.dp))
            .background(background)
            .border(
                width = 4.dp,
                color = Theme.color.primary0,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(Theme.spacing.space24),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = Theme.typography.title,
                    color = Theme.color.primary1,
                    maxLines = 2,
                    minLines = 2,
                )
                action?.let {
                    Spacer(modifier = Modifier.weight(1f))
                    AnimatedVisibility(
                        visible = isSelected,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        TertiaryIconButton(
                            modifier = Modifier,
                            icon = action,
                            onClick = onAction
                        )
                    }
                }
            }
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