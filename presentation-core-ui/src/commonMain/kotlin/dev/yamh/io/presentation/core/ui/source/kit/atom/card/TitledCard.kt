package dev.yamh.io.presentation.core.ui.source.kit.atom.card

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.core.ext.circularRevealOnTap
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryBorderButtonDefaults
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.carousel.vertical.Arrangement
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Plus32
import dev.yamh.presentation.core.styling.core.Theme


@Composable
public fun TitledCard(
    modifier: Modifier = Modifier,
    background: Color,
    title: String,
    isSelected: Boolean = false,
    action: ImageVector? = null,
    onAction: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit = {},
) {

    val color =  Theme.color.primary0
    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1.5f)
            // Do NOT clip. Draw the shape instead so children can escape.
            .background(color = background, shape = SquircleShape(32.dp))
            .drawBehind {
                val stroke = 4.dp.toPx()
                val radius = 32.dp.toPx()
                // inset so the stroke stays fully visible
                val inset = stroke / 2
                drawRoundRect(
                    color = color,
                    topLeft = androidx.compose.ui.geometry.Offset(inset, inset),
                    size = size.copy(
                        width = size.width - stroke,
                        height = size.height - stroke
                    ),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(radius, radius),
                    style = androidx.compose.ui.graphics.drawscope.Stroke(width = stroke)
                )
            }
            .padding(Theme.spacing.space16)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = title,
                    style = Theme.typography.title,
                    color = Theme.color.primary0,
                    maxLines = 1,
                    minLines = 1,
                )

                action?.let {
                    Spacer(modifier = Modifier.weight(1f))

                    AnimatedVisibility(
                        modifier = Modifier.graphicsLayer {
//                            translationY = -42.dp.toPx()
                        },
                        visible = isSelected,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        TertiaryIconButton(
                            colors = TertiaryBorderButtonDefaults.colors,
                            sizes = TertiaryBorderButtonDefaults.sizes,
                            // Lift visually without affecting measurement.
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
                    modifier = Modifier.fillMaxSize().padding(top = Theme.spacing.space8),
                    content = content
                )
            }
        }
    }
}