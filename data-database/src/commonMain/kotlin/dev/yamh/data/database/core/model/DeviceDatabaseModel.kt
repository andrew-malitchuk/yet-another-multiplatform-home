package dev.yamh.data.database.core.model

import dev.yamh.data.database.core.model.base.BaseDatabaseModel

public data class DeviceDatabaseModel(
    public val id: String,
    public val name: String,
    public val roomId: String,
    // TODO: rename to custonType
    public val type: String?,
) : BaseDatabaseModel