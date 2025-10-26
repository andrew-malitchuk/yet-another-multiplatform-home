package dev.yamh.io.data.ghome.source.datasource.room

import com.google.home.HomeClient
import com.google.home.Room
import dev.yamh.common.core.core.Color
import dev.yamh.common.core.core.Id
import dev.yamh.io.data.ghome.core.ext.getDevice
import dev.yamh.io.data.ghome.core.ext.getDevices
import dev.yamh.common.core.core.Name
import dev.yamh.io.data.ghome.source.datasource.device.DeviceModel
import dev.yamh.io.data.ghome.source.datasource.device.DeviceModel.Companion.toNewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public actual class RoomModel actual constructor(id: Id, name: Name, homeId: Id, color: Color) : KoinComponent {

    public val nativeHomeClient: HomeClient by inject()

    public actual val id: Id = id
    public actual val homeId: Id = homeId
    public actual val name: Name = name
    public actual val color: Color = color

    public actual suspend fun getDevices(): List<DeviceModel> {
        return nativeHomeClient.getDevices().filter {
            it.roomId?.id == id.value && it.structureId?.id == homeId.value
        }.map { it.toNewModel() }
    }

    public actual suspend fun getDevice(id: Id): DeviceModel? {
        return nativeHomeClient.getDevice(id = id.value)?.toNewModel()
    }

    public actual suspend fun device(id: Id): DeviceModel? {
        return getDevice(id)
    }


    internal companion object Companion {
        internal fun Room.toModel(): RoomModel {
            return RoomModel(
                id = Id(id.id),
                name = Name(name),
                homeId = Id(structureId.id),
                color = Color("")
            )
        }
    }

}