package dev.yamh.domain.core.source.model.room

import dev.yamh.common.core.core.Color
import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.data.database.core.model.RoomDatabaseModel
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel
import kotlinx.serialization.Serializable

@Serializable
public data class RoomEntity(
    public val id: Id,
    public val name: Name,
    public val homeId: Id,
    public val color: Color?,
    public var selectedDevices: List<DeviceEntity> = emptyList()
) {
    public companion object {
        public fun RoomModel.toEntity(): RoomEntity = RoomEntity(
            id = Id(this.id.value),
            name = Name(this.name.value),
            homeId = Id(this.homeId.value),
            color = Color(this.color.value)
        )

        public fun RoomDatabaseModel.toEntity(): RoomEntity {
            return RoomEntity(
                Id(this.id),
                Name(this.name),
                Id(this.homeId),
                Color(this.color)
            )
        }

        public fun RoomEntity.toDatabaseModel(): RoomDatabaseModel {
            return RoomDatabaseModel(
                id = this.id.value,
                name = this.name.value,
                homeId = this.homeId.value,
                color = this.color?.value ?: ""
            )
        }
    }
}

