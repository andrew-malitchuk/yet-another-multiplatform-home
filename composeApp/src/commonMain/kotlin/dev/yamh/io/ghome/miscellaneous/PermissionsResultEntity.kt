package dev.yamh.io.ghome.miscellaneous

public data class PermissionsResultEntity(
    val status: PermissionsResultStatusEntity,
    val errorMessage: String? = null
)