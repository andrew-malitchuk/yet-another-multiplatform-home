package dev.yamh.io.presentation.feature.splash.splash.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.loading.LoadingDots
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun SplashView (){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoadingDots(
            modifier = Modifier.fillMaxWidth().padding(Theme.size.size16),
            color = Theme.color.accent1 to Theme.color.accent2,
        )
    }
}