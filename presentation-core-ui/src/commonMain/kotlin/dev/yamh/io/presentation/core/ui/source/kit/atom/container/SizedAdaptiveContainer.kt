package dev.yamh.io.presentation.core.ui.source.kit.atom.container

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.platform.source.device.DeviceEnvironment.getOrientation
import dev.yamh.io.presentation.core.platform.source.device.DeviceEnvironment.getPosture
import dev.yamh.io.presentation.core.ui.core.layout.AdaptiveLayoutType
import dev.yamh.io.presentation.core.ui.core.layout.AdaptiveLayoutType.Companion.from
import dev.yamh.io.presentation.core.ui.core.window.WindowSizeHelper.Companion.getWindowWidthSizeClass

@Composable
public fun SizedAdaptiveContainer(
    modifier: Modifier = Modifier,
    compact: @Composable () -> Unit,
    expanded: @Composable () -> Unit,
    foldHorizontal: @Composable () -> Unit,
    foldVertical: @Composable () -> Unit,
) {

    val windowSize = getWindowWidthSizeClass()
    val deviceOrientation = getOrientation()
    val posture = getPosture()

    val type = from(
        width = windowSize,
        orientation = deviceOrientation,
        posture = posture
    )

    when (type) {
        AdaptiveLayoutType.CompactLayout -> compact()
        AdaptiveLayoutType.ExpandedLayout -> expanded()
        AdaptiveLayoutType.FoldHorizontalLayout -> foldHorizontal()
        AdaptiveLayoutType.FoldVerticalLayout -> foldVertical()
    }

}