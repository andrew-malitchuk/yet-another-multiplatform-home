package dev.yamh.io.presentation.core.ui.source.kit.molecule.bar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.primary.PrimaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.secondary.SecondaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.ArrowLeft32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.ArrowRight32
import dev.yamh.presentation.core.styling.core.Theme


@Composable
public fun TopActionAdvancedBar(
    modifier: Modifier = Modifier,
    text: String,
    action: ImageVector,
    navigation: ImageVector = ArrowLeft32,
    onNavigationClick: () -> Unit,
    onActionClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Theme.spacing.space16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        PrimaryIconButton(
            modifier = Modifier,
            icon = navigation,
            onClick = onNavigationClick
        )
        Text(
            modifier = Modifier.padding(horizontal = Theme.spacing.space8),
            text = text,
            style = Theme.typography.title,
            color = Theme.color.primary1
        )
        Spacer(modifier = Modifier.weight(1f))
        SecondaryIconButton(
            modifier = Modifier,
            icon = action,
            onClick = onActionClick
        )
    }
}