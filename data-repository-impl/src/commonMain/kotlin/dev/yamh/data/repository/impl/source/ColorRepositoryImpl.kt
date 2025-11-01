package dev.yamh.data.repository.impl.source

import dev.yamh.domain.repository.source.ColorRepository
import kotlin.math.absoluteValue

public class ColorRepositoryImpl() : ColorRepository {
    override fun getColor(): String {
        return colors.random()
    }

    override fun colorForKey(key: String): String {
        if (key.isEmpty()) return colors.first()
        val index = (key.hashCode().absoluteValue % colors.size)
        return colors[index]
    }


    private companion object {
        private const val SECONDARY0 = "#71C0DA"
        private const val SECONDARY1 = "#BDBCFA"
        private const val SECONDARY2 = "#DBC7FC"
        private const val SECONDARY3 = "#F7CEE9"
        private const val SECONDARY4 = "#E18D70"

        private const val ACCENT0 = "#FEFC54"
        private const val ACCENT1 = "#FCE64D"
        private const val ACCENT2 = "#71C0DA"
        private const val ACCENT3 = "#6C7FF7"
        private const val ACCENT4 = "#A48159"

        private val colors = listOf(
            SECONDARY0,
            SECONDARY1,
            SECONDARY2,
            SECONDARY3,
            SECONDARY4,
            ACCENT0,
            ACCENT1,
            ACCENT2,
            ACCENT3,
            ACCENT4
        )
    }

}