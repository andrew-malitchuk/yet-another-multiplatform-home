package dev.yamh.data.database.source

import dev.yamh.data.database.core.model.HomeDatabaseModel
import dev.yamh.data.database.core.model.RoomDatabaseModel
import dev.yamh.data.database.source.base.BaseDatabaseSource

public abstract class RoomDatabaseSource : BaseDatabaseSource<RoomDatabaseModel>() {

    public abstract override suspend fun save(value: RoomDatabaseModel)

    public abstract override suspend fun getAll(): List<RoomDatabaseModel>

    public abstract override suspend fun deleteAll()
}