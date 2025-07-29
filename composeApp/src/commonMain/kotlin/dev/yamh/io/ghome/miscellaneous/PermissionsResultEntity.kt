package dev.yamh.io.ghome.miscellaneous

data class PermissionsResultEntity(
    val status: PermissionsResultStatusEntity,
    val errorMessage: String? = null
)