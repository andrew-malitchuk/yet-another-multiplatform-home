package dev.yamh.domain.usecase.impl.source.room

import dev.yamh.common.core.core.Color
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.domain.repository.source.ColorRepository
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.repository.source.RoomRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.room.GetRoomsByHomeUseCase


public class GetRoomsByHomeUseCaseImpl(
    private val roomRepository: RoomRepository,
    private val colorRepository: ColorRepository,
    private val deviceRepository: DeviceRepository
) : GetRoomsByHomeUseCase {
    override suspend fun invoke(home: HomeEntity): Result<List<RoomEntity>> = resultLauncher {
        roomRepository.getRoomByHomeId(home.id)
        val remoteRooms = roomRepository.getRoomByHomeId(home.id).sortedBy { it.id.value }

        // TODO: fix
        val localRooms =
            roomRepository.loadRooms(home.id).getOrNull()?.sortedBy { it.id.value } ?: emptyList()
        var match = true

        if (remoteRooms.count() != localRooms.count()) {
            match = false
        } else {
            for (i in remoteRooms.indices) {
                if (remoteRooms[i].id != localRooms[i].id) {
                    match = false
                    break
                }
            }
        }

        val result = when (match) {
            true -> {
                localRooms.sortedBy { it.id.value }
                localRooms
            }

            false -> {
                val filledRooms = fulfillData(remoteRooms)
                roomRepository.saveRooms(filledRooms)

                filledRooms.sortedBy { it.id.value }
                filledRooms
            }
        }
        result.map { room ->
            val selectedDevices = getSelectedDevices(room)
            room.copy(
                selectedDevices = selectedDevices
            )
        }

    }

    private fun fulfillData(rooms: List<RoomEntity>): List<RoomEntity> {
        return rooms.map { room ->
            val color = if (room.color == null || room.color?.value.isNullOrEmpty()) {
                colorRepository.colorForKey(room.id.value)
            } else {
                room.color?.value
            }
            room.copy(
                color = Color(color ?: "")
            )
        }
    }

    private suspend fun getSelectedDevices(room: RoomEntity): List<DeviceEntity> {
        val ids = deviceRepository.loadDevicesByRoom(room.id).map { it.id }
        return deviceRepository.getDevicesByRoom(room.id).filter { ids.contains(it.id) }
    }
}
