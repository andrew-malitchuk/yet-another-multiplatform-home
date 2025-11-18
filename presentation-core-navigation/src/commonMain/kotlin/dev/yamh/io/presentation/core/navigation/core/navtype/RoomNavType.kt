package dev.yamh.io.presentation.core.navigation.core.navtype

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import dev.yamh.domain.core.source.model.room.RoomEntity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


public val roomNavType: NavType<RoomEntity> = object : NavType<RoomEntity>(isNullableAllowed = false) {
    override fun put(bundle: Bundle, key: String, value: RoomEntity) {
        bundle.putString(key, Json.encodeToString( value))
    }
    override fun get(bundle: Bundle, key: String): RoomEntity? =
        bundle.getString(key)?.let { Json.decodeFromString(it) }
    override fun parseValue(value: String): RoomEntity =
        Json.decodeFromString(value)
}
