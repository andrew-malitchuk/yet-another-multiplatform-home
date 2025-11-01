package dev.yamh.data.repository.impl.source

import dev.yamh.common.core.core.Id
import dev.yamh.data.database.source.RoomDatabaseSource
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.domain.core.source.model.room.RoomEntity.Companion.toDatabaseModel
import dev.yamh.domain.core.source.model.room.RoomEntity.Companion.toEntity
import dev.yamh.domain.repository.source.RoomRepository
import dev.yamh.io.data.ghome.source.datasource.HomeClientModel

public class RoomRepositoryImpl(
    private val homeClient: HomeClientModel,
    private val roomDatabaseSource: RoomDatabaseSource
) : RoomRepository {
    override suspend fun getRoomByHomeId(homeId: Id): List<RoomEntity> {
        // TODO: fix
        return homeClient.getHome(Id(homeId.value))?.getRooms()?.map {
            it.toEntity()
        } ?: emptyList()
    }

    override suspend fun loadRooms(): Result<List<RoomEntity>> {
        return runCatching {
            roomDatabaseSource.getAll().map { it.toEntity() }
        }
    }

    override suspend fun loadRooms(homeId: Id): Result<List<RoomEntity>> {
        return runCatching {
            roomDatabaseSource.getAll().filter{it.homeId == homeId.value}.map { it.toEntity() }
        }
    }

    override suspend fun saveRooms(values: List<RoomEntity>) {
        values.forEach {
            roomDatabaseSource.save(it.toDatabaseModel())
        }
    }

}