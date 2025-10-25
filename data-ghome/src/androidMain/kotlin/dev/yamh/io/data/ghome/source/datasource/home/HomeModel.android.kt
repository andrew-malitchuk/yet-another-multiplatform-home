package dev.yamh.io.data.ghome.source.datasource.home

import com.google.home.HomeClient
import com.google.home.Structure
import dev.yamh.common.core.core.Color
import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.io.data.ghome.core.ext.getRooms
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel.Companion.toModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

public actual class HomeModel actual constructor(id: Id, name: Name, color: Color) : KoinComponent {

    public val nativeHomeClient: HomeClient by inject()

    public actual val id: Id = id
    public actual val name: Name = name
    public actual val color: Color = color

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
        internal fun Structure.toModel(): HomeModel {
            return HomeModel(
                id = Id(id.id),
                name = Name(name),
                color = Color("")
            )
        }
    }
}