package dev.yamh.data.database.source

import dev.yamh.data.database.core.model.DeviceDatabaseModel
import dev.yamh.data.database.source.base.BaseDatabaseSource

public abstract class DeviceDatabaseSource : BaseDatabaseSource<DeviceDatabaseModel>() {

    public abstract override suspend fun save(value: DeviceDatabaseModel)

    public abstract override suspend fun getAll(): List<DeviceDatabaseModel>

    public abstract override suspend fun delete(value: DeviceDatabaseModel)

    public abstract override suspend fun deleteAll()
}