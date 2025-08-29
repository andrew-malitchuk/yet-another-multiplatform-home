package dev.yamh.io.presentation.core.ui.core.window

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable


public class WindowSizeHelper {
    public companion object {

        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        @Composable
        public fun getWindowWidthSizeClass(): WindowSize {
            val windowSizeClass = calculateWindowSizeClass()
            return when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> WindowSize.COMPACT
                WindowWidthSizeClass.Medium -> WindowSize.MEDIUM
                WindowWidthSizeClass.Expanded -> WindowSize.EXPANDED
                else -> WindowSize.COMPACT
            }
        }

        @Composable
        public fun DetectSize(
            compact: @Composable () -> Unit,
            medium: @Composable () -> Unit,
            expanded: @Composable () -> Unit
        ) {
            when (getWindowWidthSizeClass()) {
                WindowSize.COMPACT -> compact()
                WindowSize.MEDIUM -> medium()
                WindowSize.EXPANDED -> expanded()
            }
        }
    }
}

