package dev.yamh.data.database.impl.core.mapper

import dev.yamh.data.database.core.model.RoomDatabaseModel
import kotbase.Dictionary
import kotbase.MutableDocument

internal fun RoomDatabaseModel.toMutableDocument(): MutableDocument {
    return MutableDocument(this.id).apply {
        setString("id", this@toMutableDocument.id)
        setString("name", this@toMutableDocument.name)
        setString("homeId", this@toMutableDocument.homeId)
        setString("color", this@toMutableDocument.color)
    }
}

internal fun Dictionary.toRoomDatabaseModel(): RoomDatabaseModel {
    return RoomDatabaseModel(
        id = this.getString("id") ?: "",
        name = this.getString("name") ?: "",
        homeId = this.getString("homeId") ?: "",
        color = this.getString("color") ?: ""
    )
}