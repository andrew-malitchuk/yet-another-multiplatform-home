package dev.yamh.io.presentation.feature.device.source.switcher.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class SwitcherIntent : MviIntent {
    public data class Setup(val device: DeviceEntity) : SwitcherIntent()
    public data object OnBackClickIntent : SwitcherIntent()
    public data object GoToMainIntent : SwitcherIntent()
    public data class GoToSettingsIntent(val device: DeviceEntity) : SwitcherIntent()
    public data class ChangeAttribute(val attribute: AttributeEntity) : SwitcherIntent()
}