package dev.yamh.io.presentation.feature.device.source.contact.mvi

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.io.presentation.core.platform.source.mvi.MviState

public data class ContactState(
    val isLoading: Boolean = false,
    val device: DeviceEntity? = null,
    val isOpen: Boolean? = null,
    val customType: DeviceCustomType? = null
) : MviState