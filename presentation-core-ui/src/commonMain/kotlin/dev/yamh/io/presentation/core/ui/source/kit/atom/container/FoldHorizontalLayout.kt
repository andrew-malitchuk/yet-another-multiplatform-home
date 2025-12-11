package dev.yamh.io.presentation.core.ui.source.kit.atom.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun FoldHorizontalLayout(
    modifier: Modifier = Modifier,
    start: @Composable () -> Unit,
    end: @Composable () -> Unit,
) {
    Row(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().weight(1f), content = { start() })
        Box(modifier = Modifier.fillMaxWidth().weight(1f), content = { end() })
    }
}