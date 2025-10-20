package dev.yamh.data.database.core.model

import dev.yamh.data.database.core.model.base.BaseDatabaseModel

public data class HomeDatabaseModel(
    val id: String,
    val name: String,
    val selected: Boolean,
    val color: String
) : BaseDatabaseModel