package dev.yamh.data.database.core.model

import dev.yamh.data.database.core.model.base.BaseDatabaseModel

public class RoomDatabaseModel(
    public val id: String,
    public val name: String,
    public val homeId: String,
    public val color: String
) : BaseDatabaseModel