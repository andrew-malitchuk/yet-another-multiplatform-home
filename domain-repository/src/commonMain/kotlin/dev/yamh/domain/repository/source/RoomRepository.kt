package dev.yamh.domain.repository.source

import dev.yamh.common.core.core.Id
import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.core.source.model.room.RoomEntity

public interface RoomRepository {
    public suspend fun getRoomByHomeId(homeId: Id): List<RoomEntity>
    public suspend fun loadRooms(): Result<List<RoomEntity>>
    public suspend fun loadRooms(homeId: Id): Result<List<RoomEntity>>
    public suspend fun saveRooms(values: List<RoomEntity>)
}