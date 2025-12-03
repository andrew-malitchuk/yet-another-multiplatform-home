package dev.yamh.io.presentation.feature.device.navigation

import kotlinx.serialization.Serializable

@Serializable
public sealed class DeviceInnerNavigationDirection {
    @Serializable
    public data class Switcher(val device: String? = null) : DeviceInnerNavigationDirection()

    @Serializable
    public data class Settings(val device: String? = null) : DeviceInnerNavigationDirection()
}