package dev.yamh.data.database.source

import dev.yamh.data.database.core.model.HomeDatabaseModel
import dev.yamh.data.database.source.base.BaseDatabaseSource

public abstract class HomeDatabaseSource : BaseDatabaseSource<HomeDatabaseModel>() {

    public abstract override suspend fun save(value: HomeDatabaseModel)

    public abstract override suspend fun getAll(): List<HomeDatabaseModel>

    public abstract override suspend fun deleteAll()
}