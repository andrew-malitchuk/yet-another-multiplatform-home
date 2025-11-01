package dev.yamh.data.repository.impl.source

import dev.yamh.data.preference.source.HomePreferenceSource
import dev.yamh.common.core.core.Id
import dev.yamh.data.database.source.HomeDatabaseSource
import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.core.source.model.home.HomeEntity.Companion.toDatabaseModel
import dev.yamh.domain.core.source.model.home.HomeEntity.Companion.toEntity
import dev.yamh.domain.repository.source.HomeRepository
import dev.yamh.io.data.ghome.source.datasource.HomeClientModel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

public class HomeRepositoryImpl(
    private val homeClient: HomeClientModel,
    private val homePreferenceSource: HomePreferenceSource,
    private val homeDatabaseSource: HomeDatabaseSource
) : HomeRepository {

    override suspend fun getSelectedHomes(): HomeEntity? {
        return homePreferenceSource.getSelectedHome()?.let {
            Json.decodeFromString<HomeEntity>(it)
        }
    }

    override suspend fun setSelectedHomes(home: HomeEntity?) {
        homePreferenceSource.setSelectedHome(
            Json.encodeToString(home)
        )
    }


    override suspend fun getHomes(): List<HomeEntity> {
        return homeClient.getHomes().map {
            it.toEntity()
        }
    }

    override suspend fun loadHomes(): Result<List<HomeEntity>> {
        return runCatching {
            homeDatabaseSource.getAll().map { it.toEntity() }
        }
    }

    override suspend fun saveHomes(values: List<HomeEntity>) {
        values.forEach {
            homeDatabaseSource.save(it.toDatabaseModel())
        }
    }

}