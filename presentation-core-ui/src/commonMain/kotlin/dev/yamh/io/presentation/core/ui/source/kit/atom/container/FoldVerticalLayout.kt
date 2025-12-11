package dev.yamh.io.presentation.core.ui.source.kit.atom.container

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public fun FoldVerticalLayout(
    modifier: Modifier = Modifier,
    top: @Composable () -> Unit,
    bottom: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().weight(1f), content = { top() })
        Box(modifier = Modifier.fillMaxWidth().weight(1f), content = { bottom() })
    }
}