package dev.yamh.io.presentation.core.ui.source.kit.molecule.bar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.primary.PrimaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.secondary.SecondaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.ArrowLeft32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Cancel32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Search32
import dev.yamh.io.presentation.core.ui.source.kit.atom.input.InputField
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun TopSearchBar(
    modifier: Modifier = Modifier,
    text: String? = null,
    onNavClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
) {
    var isSearchMode by remember { mutableStateOf(false) }

    val query = remember { mutableStateOf("") }

    if (!isSearchMode) {
        query.value = ""
        onValueChange("")
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Theme.spacing.space16),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AnimatedVisibility(
            visible = !isSearchMode,
            enter = androidx.compose.animation.fadeIn(),
            exit = androidx.compose.animation.fadeOut()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                onNavClick?.let {
                    PrimaryIconButton(
                        modifier = Modifier,
                        icon = ArrowLeft32,
                        onClick = onNavClick
                    )
                }
                Spacer(modifier = Modifier.width(Theme.spacing.space8))
                text?.let {
                    Text(
                        text = it,
                        style = Theme.typography.title,
                        color = Theme.color.primary1
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                SecondaryIconButton(
                    modifier = Modifier,
                    icon = Search32,
                    onClick = { isSearchMode = true }
                )
            }
        }

        AnimatedVisibility(
            visible = isSearchMode,
            enter = androidx.compose.animation.fadeIn(),
            exit = androidx.compose.animation.fadeOut()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                InputField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = Theme.spacing.space8),
                    value = query.value,
                    onValueChange = {
                        query.value = it
                        onValueChange(it)
                    }
                )
                SecondaryIconButton(
                    modifier = Modifier,
                    icon = Cancel32,
                    onClick = { isSearchMode = false }
                )
            }
        }
    }
}

