package dev.yamh.io.presentation.core.ui.source.kit.organism.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.localisation.source.Localisation
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.tertiary.TertiaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.TitledCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.ExternalLink24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Plus32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Slash32
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import dev.yamh.io.presentation.core.ui.source.kit.molecule.row.EdgedLazyRow
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun RoomCard(
    modifier: Modifier = Modifier,
    title: String,
    background: Color,
    isSelected: Boolean,
    isEmpty: Boolean,
    onEmptyClick: () -> Unit = {},
    onAction: () -> Unit = {},
    content: LazyListScope.() -> Unit
) {

    val localisation = LocalLocalisation.current

    TitledCard(
        modifier = modifier,
        background = background,
        title = title,
        action = ExternalLink24,
        onAction = onAction,
        isSelected = isSelected,
    ) {
        when (isEmpty) {
            true -> Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(SquircleShape(32.dp))
                    .background(Theme.color.accent2)
                    .border(4.dp, Theme.color.primary0, SquircleShape(32.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        modifier = Modifier.size(32.dp),
                        imageVector = Slash32,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Theme.color.primary0)
                    )
                    Text(
                        modifier = Modifier,
                        text = localisation.general.empty,
                        style = Theme.typography.title,
                        color = Theme.color.primary0,
                        maxLines = 1,
                        minLines = 1,
                    )
                }
            }

            false -> EdgedLazyRow(
                modifier = Modifier,
                content = content
            )
        }

    }
}