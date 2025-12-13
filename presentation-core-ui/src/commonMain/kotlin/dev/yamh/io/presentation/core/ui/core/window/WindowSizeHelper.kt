package dev.yamh.io.presentation.core.ui.core.window

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import dev.yamh.io.presentation.core.platform.source.device.DeviceEnvironment
import dev.yamh.io.presentation.core.platform.source.device.DeviceEnvironment.getOrientation
import dev.yamh.io.presentation.core.platform.source.device.DeviceEnvironment.getPosture
import dev.yamh.io.presentation.core.platform.source.device.DeviceOrientation
import dev.yamh.io.presentation.core.platform.source.device.DevicePosture
import kotlin.getValue


public class WindowSizeHelper {


    public companion object {


        @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
        @Composable
        public fun getWindowWidthSizeClass(): WindowSize {
            val windowSizeClass = calculateWindowSizeClass()

            println("size | ${windowSizeClass.widthSizeClass} | ${windowSizeClass.heightSizeClass}")

            return when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> WindowSize.COMPACT
                WindowWidthSizeClass.Medium -> WindowSize.MEDIUM
                WindowWidthSizeClass.Expanded -> WindowSize.EXPANDED
                else -> WindowSize.COMPACT
            }
        }


        @Composable
        public fun DetectSize(
            compact: @Composable (DeviceOrientation, DevicePosture) -> Unit,
            medium: @Composable (DeviceOrientation, DevicePosture) -> Unit,
            expanded: @Composable (DeviceOrientation, DevicePosture) -> Unit
        ) {
            val windowSize = getWindowWidthSizeClass()
            val deviceOrientation = getOrientation()
            val posture = getPosture()

            println("device: ${windowSize} | ${deviceOrientation} | ${posture}")

            when (windowSize) {
                WindowSize.COMPACT -> compact(deviceOrientation, posture)
                WindowSize.MEDIUM -> medium(deviceOrientation, posture)
                WindowSize.EXPANDED -> expanded(deviceOrientation, posture)
            }
        }
    }
}