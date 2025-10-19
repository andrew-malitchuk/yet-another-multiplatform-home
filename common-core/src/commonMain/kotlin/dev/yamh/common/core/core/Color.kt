package dev.yamh.common.core.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class Color(
    public val value: String
)

public fun String.toColor(): Color? {
    return when {
        this.isBlank() -> null
        else -> Color(this)
    }
}