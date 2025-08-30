package dev.yamh.io.presentation.core.ui.source.kit.atom.button.core

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable

@Immutable
public interface ButtonAnimation {
    public val duration: Int
    public val easing: Easing
}

public data class ButtonAnimationConfigure(
    val duration: Int,
    val easing: Easing
) {
    public fun getButtonAnimation(): ButtonAnimation {
        return object : ButtonAnimation {
            override val duration: Int = this@ButtonAnimationConfigure.duration
            override val easing: Easing = this@ButtonAnimationConfigure.easing
        }
    }
}