package dev.yamh.data.database.impl.source

import dev.yamh.data.database.core.model.HomeDatabaseModel
import dev.yamh.data.database.core.model.base.BaseDatabaseModel
import dev.yamh.data.database.impl.core.mapper.toHomeDatabaseModel
import dev.yamh.data.database.impl.core.mapper.toMutableDocument
import dev.yamh.data.database.source.HomeDatabaseSource
import kotbase.ConcurrencyControl
import kotbase.ConflictHandler
import kotbase.DataSource
import kotbase.Database
import kotbase.Expression
import kotbase.Meta
import kotbase.MutableDictionary
import kotbase.MutableDocument
import kotbase.QueryBuilder
import kotbase.SelectResult
import kotbase.get
import kotlin.coroutines.suspendCoroutine

public class HomeDatabaseSourceImpl(
    private val database: Database
) : HomeDatabaseSource() {

    override suspend fun save(value: HomeDatabaseModel) {
        return suspendCoroutine {
            it.resumeWith(runCatching {
                val collection = database.createCollection("home")
                collection.save(
                    value.toMutableDocument(),
                )
            })
        }
    }

    override suspend fun getAll(): List<HomeDatabaseModel> {
        return suspendCoroutine {
            val result = mutableListOf<HomeDatabaseModel>()

            val collection = database.createCollection("home")
            val queryAll = QueryBuilder
                .select(SelectResult.all())
                .from(DataSource.collection(collection))

            queryAll.execute().use { query ->
                query.forEach { item ->
                    item.getDictionary("home")?.run {
                        result.add(toHomeDatabaseModel())
                    }
                }
            }
            it.resumeWith(Result.success(result))
        }
    }

    override suspend fun delete(value: HomeDatabaseModel) {
        return suspendCoroutine {
            val collection = database.createCollection("home")
            val query = QueryBuilder
                .select(SelectResult.all())
                .from(DataSource.collection(collection))
            query.execute().use { results ->
                results.allResults().forEach { result ->
                    result.getString("id")?.let { id ->
                        collection.getDocument(id)?.let { doc ->
                            if (value.id == doc.id) {
                                collection.delete(doc)
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun deleteAll() {
        return suspendCoroutine {
            val collection = database.createCollection("home")
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