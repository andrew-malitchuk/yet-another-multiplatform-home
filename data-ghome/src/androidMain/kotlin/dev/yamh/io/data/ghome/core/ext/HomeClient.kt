package dev.yamh.io.data.ghome.core.ext

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.DeviceType
import com.google.home.HomeClient
import com.google.home.HomeDevice
import com.google.home.Room
import com.google.home.Structure
import com.google.home.Trait
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlin.reflect.KClass

internal suspend fun HomeClient.getStructures(): Set<Structure> {
    return structures()
        .firstOrNull()
        ?.takeIf { it.isNotEmpty() }
        ?: emptySet()
}

internal fun HomeClient.getStructuresFlow(): StateFlow<Set<Structure>> {
    return structures()
        .stateIn(
            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default),
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            emptySet()
        )
}

internal suspend fun HomeClient.getStructure(id: String): Structure? {
    return structures()
        .firstOrNull()
        ?.firstOrNull { it.id.id == id }
}

internal fun HomeClient.getStructureFlow(id: String): StateFlow<Structure?> {
    return structures()
        .map { structures -> structures.firstOrNull { it.id.id == id } }
        .stateIn(
            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default),
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            null
        )
}

internal suspend fun HomeClient.getRooms(): Set<Room> {
    return rooms()
        .firstOrNull()
        ?.takeIf { it.isNotEmpty() }
        ?: emptySet()
}



internal fun HomeClient.getRoomsFlow(): StateFlow<Set<Room>> {
    return rooms()
        .stateIn(
            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default),
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            emptySet()
        )
}

internal suspend fun HomeClient.getRoom(id: String): Room? {
    return rooms()
        .firstOrNull()
        ?.firstOrNull { it.id.id == id }
}

internal fun HomeClient.getRoomsFlow(id: String): StateFlow<Room?> {
    return rooms()
        .map { rooms -> rooms.firstOrNull { it.id.id == id } }
        .stateIn(
            kotlinx.coroutines.CoroutineScope(kotlinx.coroutines.Dispatchers.Default),
            kotlinx.coroutines.flow.SharingStarted.Eagerly,
            null
        )
}

internal suspend fun HomeClient.getRooms(structureId: String): Set<Room> {
    return rooms()
        .firstOrNull()
        ?.filter { it.structureId.id == structureId }
        ?.toSet()
        ?: emptySet()
}


internal suspend fun HomeClient.getDevices(): Set<HomeDevice> {
    return devices()
        .firstOrNull()
        ?.takeIf { it.isNotEmpty() }
        ?: emptySet()
}

internal suspend fun HomeClient.getDevices(roomId: String): Set<HomeDevice> {
    return devices()
        .firstOrNull()
        ?.filter { it.roomId?.id == roomId }
        ?.toSet()
        ?: emptySet()
}


internal suspend fun HomeClient.getDevice(id: String): HomeDevice? {
    return devices()
        .firstOrNull()
        ?.firstOrNull { it.id.id == id }
}


internal suspend fun HomeClient.getDeviceTypes(id: String): Set<DeviceType>? {
    return getDevice(id)?.types()?.firstOrNull()
}

internal suspend fun HomeClient.getDeviceTrait(id: String, origin: KClass<*>): Trait? {
    return getDeviceTypes(id)?.firstOrNull()?.traits()?.firstOrNull {
        origin.isInstance(it)
    }
}

