package dev.yamh.io.presentation.feature.device.source.window.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class WindowIntent : MviIntent {
    public data class Setup(val device: DeviceEntity) : WindowIntent()
    public data object OnBackClickIntent : WindowIntent()
    public data object GoToMainIntent : WindowIntent()
    public data class GoToSettingsIntent(val device: DeviceEntity) : WindowIntent()
    public data class ChangeAttribute(val attribute: AttributeEntity) : WindowIntent()
}