package dev.yamh.io.presentation.core.ui.source.kit.atom.container

import StackedSnackbarHost
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun SafeContainer(
    modifier: Modifier = Modifier,
    snackbarHost: @Composable (SnackbarHostState) -> Unit = { SnackbarHost(it) },
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        snackbarHost = snackbarHost,
        modifier = modifier
            .fillMaxSize()
            .background(Theme.color.primary0)
            .navigationBarsPadding()
            .statusBarsPadding(),
        content = content,
        backgroundColor = Theme.color.primary0
    )
}