package dev.yamh.domain.core.source.model.device

import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.data.database.core.model.DeviceDatabaseModel
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.io.data.ghome.source.datasource.device.DeviceModel
import kotlinx.serialization.Serializable

@Serializable
public data class DeviceEntity(
    val id: Id,
    val name: Name,
    val homeId: Id?,
    val roomId: Id?,
    val originalId: Id?,
    val type: List<DeviceType?>,
    val customType: DeviceCustomType? = null,
    var attribute: AttributeEntity? = null
) {
    public fun generalType(): GeneralDeviceType? {
        return when {

            type.contains(DeviceType.TemperatureSensor) -> GeneralDeviceType.Temperature

            type.contains(DeviceType.Dimmable)
                    && type.contains(DeviceType.OnOff)
                    && type.contains(DeviceType.ColorControl)
                -> GeneralDeviceType.Light

            type.contains(DeviceType.OnOff) -> GeneralDeviceType.Switch
            type.contains(DeviceType.Contact) -> GeneralDeviceType.Contact
            type.contains(DeviceType.WindowCovering) -> GeneralDeviceType.WindowCovering
            else -> null
        }
    }
}

public enum class GeneralDeviceType {
    Light,
    Switch,
    Temperature,
    Contact,
    WindowCovering,
}

public fun GeneralDeviceType.toDeviceType(): DeviceType {
    return when (this) {
        GeneralDeviceType.Light -> DeviceType.OnOff
        GeneralDeviceType.Switch -> DeviceType.OnOff
        GeneralDeviceType.Temperature -> DeviceType.TemperatureSensor
        GeneralDeviceType.Contact -> DeviceType.Contact
        GeneralDeviceType.WindowCovering -> DeviceType.WindowCovering
    }
}


public fun DeviceModel.toNewEntity(): DeviceEntity = DeviceEntity(
    id = Id(this.id.value),
    name = Name(this.name.value),
    homeId = this.homeId?.let { Id(it.value) },
    roomId = this.roomId?.let { Id(it.value) },
    originalId = this.originalId?.let { Id(it.value) },
    type = this.type.map { newType ->
        DeviceType.entries.firstOrNull {
            it.name == newType.name
        }
    }.distinct(),
)


public fun DeviceDatabaseModel.toEntity(): DeviceEntity = DeviceEntity(
    id = Id(this.id),
    name = Name(this.name),
    homeId = null,
    roomId = null,
    originalId = null,
    type = emptyList(),
    customType = DeviceCustomType.entries.firstOrNull { it.typeName == this.type }
)