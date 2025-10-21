package dev.yamh.data.database.source.base

import dev.yamh.data.database.core.model.DeviceDatabaseModel
import dev.yamh.data.database.core.model.HomeDatabaseModel
import dev.yamh.data.database.core.model.base.BaseDatabaseModel

public abstract class BaseDatabaseSource<T : BaseDatabaseModel> {
    public abstract suspend fun save(value: T)

    public abstract suspend fun getAll(): List<T>

    public abstract suspend fun delete(value: T)

    public abstract suspend fun deleteAll()
}