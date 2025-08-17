package dev.yamh.io.data.ghome.ghome.foobar.home

import dev.yamh.io.data.ghome.ghome.foobar.room.RoomModel
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.core.Name

public expect class HomeModel(id: Id, name: Name) {

    public val id: Id
    public val name: Name

    public suspend fun getRooms(): List<RoomModel>
    public suspend fun getRoom(id: Id): RoomModel?
    public suspend fun room(id: Id): RoomModel?
}
