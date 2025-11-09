package dev.yamh.domain.usecase.source.room

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.core.source.model.room.RoomEntity

public interface GetRoomsByHomeUseCase {
    public suspend operator fun invoke(home: HomeEntity): Result<List<RoomEntity>>
}