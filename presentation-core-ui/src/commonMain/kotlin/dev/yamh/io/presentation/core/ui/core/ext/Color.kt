package dev.yamh.io.presentation.core.ui.core.ext

import androidx.compose.ui.graphics.Color


/**
 * Converts a hexadecimal color string to a [Color].
 *
 * Accepts `#RRGGBB` or `#AARRGGBB` formats (leading `#` is optional).
 * If a 6-digit `RRGGBB` value is provided, the alpha channel is assumed to be `FF` (opaque).
 *
 * @return The corresponding [Color].
 * @throws IllegalArgumentException if [hex] does not contain exactly 6 or 8 hex digits.
 */
public fun dev.yamh.common.core.core.Color.toColor(): Color? {
    val cleanedHex = this.value.removePrefix("#")
    print("cleanedHex: $cleanedHex")
    val colorLong = cleanedHex.toLong(16)
    return when (cleanedHex.length) {
        6 -> Color(colorLong or 0x00000000FF000000) // Add alpha = 255
        8 -> Color(colorLong)
        else -> null
    }
}
