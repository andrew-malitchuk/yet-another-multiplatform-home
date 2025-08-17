package dev.yamh.io.data.ghome.ghome.foobar.home

import dev.yamh.io.data.ghome.ghome.foobar.room.RoomModel
import dev.yamh.io.data.ghome.ghome.foobar.core.Id

public actual class HomeModel actual constructor(id: Id, name: String) {

    public actual val id: Id = id
    public actual val name: String = name

    public actual suspend fun getRooms(): List<RoomModel> {
        TODO("Not yet implemented")
    }

    public actual suspend fun getRoom(id: Id): RoomModel? {
        TODO("Not yet implemented")
    }

    public actual suspend fun room(id: Id): RoomModel? {
        TODO("Not yet implemented")
    }

}