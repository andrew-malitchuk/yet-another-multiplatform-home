package dev.yamh.domain.repository.source

import dev.yamh.common.core.core.Id
import dev.yamh.domain.core.source.model.home.HomeEntity
import kotlinx.coroutines.flow.Flow

public interface HomeRepository {
    public suspend fun getSelectedHomes(): HomeEntity?
    public suspend fun setSelectedHomes(home: HomeEntity?)
    public suspend fun getHomes(): List<HomeEntity>

    public suspend fun loadHomes(): Result<List<HomeEntity>>
    public suspend fun saveHomes(values: List<HomeEntity>)
}