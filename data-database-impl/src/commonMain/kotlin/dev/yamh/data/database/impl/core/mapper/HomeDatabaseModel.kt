package dev.yamh.data.database.impl.core.mapper

import dev.yamh.data.database.core.model.HomeDatabaseModel
import kotbase.Dictionary
import kotbase.MutableDocument

internal fun HomeDatabaseModel.toMutableDocument(): MutableDocument {
    return MutableDocument(this.id).apply {
        setString("id", this@toMutableDocument.id)
        setString("name", this@toMutableDocument.name)
        setBoolean("selected", this@toMutableDocument.selected)
        setString("color", this@toMutableDocument.color)
    }
}

internal fun Dictionary.toHomeDatabaseModel(): HomeDatabaseModel {
    return HomeDatabaseModel(
        id = this.getString("id") ?: "",
        name = this.getString("name") ?: "",
        selected = this.getBoolean("selected"),
        color = this.getString("color") ?: ""
    )
}