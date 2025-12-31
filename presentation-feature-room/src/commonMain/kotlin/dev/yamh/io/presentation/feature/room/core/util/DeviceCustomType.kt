package dev.yamh.io.presentation.feature.room.core.util

import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType

public fun DeviceCustomType.toDeviceType(): DeviceType? {
    return DeviceType.entries.firstOrNull {
        it.typeName == this.typeName
    }
}