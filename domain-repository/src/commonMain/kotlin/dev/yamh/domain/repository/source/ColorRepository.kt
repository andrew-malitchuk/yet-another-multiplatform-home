package dev.yamh.domain.repository.source


public interface ColorRepository {
    public fun getColor(): String
    public fun colorForKey(key: String): String
}
