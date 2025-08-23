package dev.yamh.io.data.ghome.source.datasource.home

import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel


public expect class HomeModel(id: Id, name: Name) {

    public val id: Id
    public val name: Name

    public suspend fun getRooms(): List<RoomModel>
    public suspend fun getRoom(id: Id): RoomModel?
    public suspend fun room(id: Id): RoomModel?
}
