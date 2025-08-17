package dev.yamh.io.data.ghome.ghome.foobar.home

import com.google.home.HomeClient
import com.google.home.Structure
import dev.yamh.io.data.ghome.core.ext.getRooms
import dev.yamh.io.data.ghome.ghome.foobar.room.RoomModel
import dev.yamh.io.data.ghome.ghome.foobar.room.RoomModel.Companion.toModel
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.core.Name
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

public actual class HomeModel actual constructor(id: Id, name: Name) : KoinComponent {

    public val nativeHomeClient: HomeClient by inject()

    public actual val id: Id = id
    public actual val name: Name = name

    public actual suspend fun getRooms(): List<RoomModel> {
        return nativeHomeClient.getRooms().filter { it.structureId.id == id.value }
            .map { it.toModel() }
    }

    public actual suspend fun getRoom(id: Id): RoomModel? {
        return nativeHomeClient
            .getRooms()
            .firstOrNull { it.id.id == id.value }
            ?.toModel()
    }

    public actual suspend fun room(id: Id): RoomModel? {
        return getRoom(id)
    }

    internal companion object Companion {
        internal fun Structure.toHomeModel(): HomeModel {
            return HomeModel(
                id = Id(id.id),
                name = Name(name),
            )
        }
    }
}