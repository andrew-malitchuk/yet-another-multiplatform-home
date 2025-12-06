package dev.yamh.io.presentation.core.ui.source.kit.organism.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.io.presentation.core.localisation.source.provider.LocalLocalisation
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.TitledCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.ExternalLink24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Slash32
import dev.yamh.io.presentation.core.ui.source.kit.atom.shape.SquircleShape
import dev.yamh.io.presentation.core.ui.source.kit.molecule.row.EdgedLazyRow
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun DeviceDetailsCard(
    modifier: Modifier = Modifier,
    id: Id?,
    name: Name?,
    homeId: Id?,
    roomId: Id?,
) {

    val localisation = LocalLocalisation.current

    @Composable
    fun InfoRow(label: String, value: String) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                style = Theme.typography.caption,
                color = Theme.color.primary0,
                maxLines = 1,
                minLines = 1,
            )
            Spacer(modifier = Modifier.width(Theme.spacing.space8))
            Text(
                modifier = Modifier.weight(1f),
                text = value,
                style = Theme.typography.caption,
                color = Theme.color.primary0,
                maxLines = 1,
                minLines = 1,
                textAlign = TextAlign.End
            )
        }
    }

    TitledCard(
        modifier = modifier,
        background = Theme.color.accent2,
        title = localisation.settings.information,
        isSelected = true,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(SquircleShape(32.dp))
                .background(Theme.color.accent1)
                .border(4.dp, Theme.color.primary0, SquircleShape(32.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Theme.spacing.space24),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {

                id?.let {
                    InfoRow(label = "[id]", value = it.value)
                }
                name?.let {
                    InfoRow(label = "[name]", value = it.value)
                }
                homeId?.let {
                    InfoRow(label = "[home-id]", value = it.value)
                }
                roomId?.let {
                    InfoRow(label = "[room-id]", value = it.value)
                }

            }

        }

    }
}