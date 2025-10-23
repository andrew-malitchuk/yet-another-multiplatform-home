package dev.yamh.data.database.impl.source

import dev.yamh.data.database.core.model.DeviceDatabaseModel
import dev.yamh.data.database.impl.core.mapper.toDeviceDatabaseModel
import dev.yamh.data.database.impl.core.mapper.toMutableDocument
import dev.yamh.data.database.source.DeviceDatabaseSource
import kotbase.DataSource
import kotbase.Database
import kotbase.QueryBuilder
import kotbase.SelectResult
import kotlin.coroutines.suspendCoroutine

public class DeviceDatabaseSourceImpl(
    private val database: Database
) : DeviceDatabaseSource() {

    override suspend fun save(value: DeviceDatabaseModel) {
        return suspendCoroutine {
            it.resumeWith(runCatching {
                val collection = database.createCollection("Device")
                collection.save(
                    value.toMutableDocument(),
                )
            })
        }
    }

    override suspend fun getAll(): List<DeviceDatabaseModel> {
        return suspendCoroutine {
            val result = mutableListOf<DeviceDatabaseModel>()

            val collection = database.createCollection("Device")
            val queryAll = QueryBuilder
                .select(SelectResult.all())
                .from(DataSource.collection(collection))

            queryAll.execute().use { query ->
                query.forEach { item ->
                    item.getDictionary("Device")?.run {
                        result.add(toDeviceDatabaseModel())
                    }
                }
            }
            it.resumeWith(Result.success(result))
        }
    }

    override suspend fun delete(value: DeviceDatabaseModel) {
        return suspendCoroutine {
            it.resumeWith(runCatching {
                val collection = database.createCollection("Device")
                val query = QueryBuilder
                    .select(SelectResult.all())
                    .from(DataSource.collection(collection))
                query.execute().use { results ->
                    results.allResults().forEach { result ->
                        result.getDictionary("Device")?.getString("id")?.let { id ->
                            collection.getDocument(id)?.let { doc ->
                                if (value.id == doc.id) {
                                    collection.delete(doc)
                                }
                            }
                        }
                    }
                }
            })
        }
    }

    override suspend fun deleteAll() {
        return suspendCoroutine {
            val collection = database.createCollection("Device")
            val query = QueryBuilder
                .select(SelectResult.all())
                .from(DataSource.collection(collection))
            query.execute().use { results ->
                results.allResults().forEach { result ->
                    result.getString("id")?.let { id ->
                        collection.getDocument(id)?.let { doc ->
                            collection.delete(doc)
                        }
                    }
                }
            }
        }
    }

}