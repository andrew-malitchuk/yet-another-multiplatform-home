package dev.yamh.data.database.impl.core.mapper

import dev.yamh.data.database.core.model.DeviceDatabaseModel
import dev.yamh.data.database.core.model.HomeDatabaseModel
import kotbase.Dictionary
import kotbase.MutableDocument

internal fun DeviceDatabaseModel.toMutableDocument(): MutableDocument {
    return MutableDocument(this.id).apply {
        setString("id", this@toMutableDocument.id)
        setString("name", this@toMutableDocument.name)
        setString("roomId", this@toMutableDocument.roomId)
        setString("type", this@toMutableDocument.type)
    }
}

internal fun Dictionary.toDeviceDatabaseModel(): DeviceDatabaseModel {
    return DeviceDatabaseModel(
        id = this.getString("id") ?: "",
        name = this.getString("name") ?: "",
        roomId = this.getString("roomId") ?: "",
        type = this.getString("type") ?: "",
    )
}