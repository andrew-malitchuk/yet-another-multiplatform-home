package dev.yamh.io.presentation.core.ui.source.kit.organism.card

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.core.ext.noRippleClickable
import dev.yamh.io.presentation.core.ui.source.kit.atom.text.AutoSizeText
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun DeviceCard(
    modifier: Modifier = Modifier,
    title: String,
    background: Color,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clip(SquircleShape(32.dp))
            .background(background)
            .border(4.dp, Theme.color.primary0, SquircleShape(32.dp))
            .noRippleClickable(onClick),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(8.dp).weight(1f).fillMaxWidth()
                    .clip(SquircleShape(32.dp))
                    .background(Theme.color.primary0)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
            AutoSizeText(
                modifier = Modifier
                    .basicMarquee()
                    .padding(
                        bottom = Theme.spacing.space8,
                        start = Theme.spacing.space16,
                        end = Theme.spacing.space16,
                    ),
                text = title,
                style = Theme.typography.caption,
                color = Theme.color.primary0,
                maxLines = 1,
                minLines = 1,
            )

        }
    }

}