package dev.yamh.common.core.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class Name(
    public val value: String
)