package dev.yamh.io.presentation.core.platform.source.device

import androidx.compose.runtime.Composable


public enum class DeviceOrientation { LANDSCAPE, PORTRAIT }
public enum class DevicePosture { NORMAL, HALF_OPENED, FLAT }

public expect object DeviceEnvironment {
    @Composable
    public fun getOrientation(): DeviceOrientation
    @Composable
    public fun getPosture(): DevicePosture
}