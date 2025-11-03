package dev.yamh.domain.core.source.model

public enum class ThemeEntity constructor(public val isDark: Boolean) {
    LIGHT(false),
    DARK(true),
}

public fun ThemeEntity.isDark(): Boolean = this.isDark
