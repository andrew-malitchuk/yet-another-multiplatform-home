package dev.yamh.io.data.ghome.source.datasource

import dev.yamh.common.core.core.Id
import dev.yamh.io.data.ghome.source.datasource.device.attribute.AttributeModel
import dev.yamh.io.data.ghome.source.datasource.home.HomeModel
import dev.yamh.io.data.ghome.source.datasource.room.RoomModel
import kotlinx.coroutines.flow.Flow

public expect class HomeClientModel {
    public constructor()

    public suspend fun getHomes(): List<HomeModel>
    public fun getHomesFlow(): Flow<List<HomeModel>>
    public suspend fun getHome(id: Id): HomeModel?
    public fun getHomeFlow(id: Id): Flow<HomeModel?>
    public suspend fun home(id: Id): HomeModel?
    public fun homeFlow(id: Id): Flow<HomeModel?>

    public fun getRoomFlow(id: Id): Flow<RoomModel?>
    public suspend fun getRoom(id: Id): RoomModel?

    public suspend fun subscribeAttributeChanges(
        id: Id,
        type: dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType?
    ): Flow<AttributeModel>

    public suspend fun getDeviceAttribute(
        id: Id,
        type: dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType?
    ): AttributeModel?

    public suspend fun changeDeviceAttribute(
        id: Id,
        type: dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType?,
        attribute: AttributeModel
    )

}