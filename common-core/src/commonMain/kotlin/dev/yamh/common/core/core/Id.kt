package dev.yamh.common.core.core

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
public value class Id(
    public val value: String
)