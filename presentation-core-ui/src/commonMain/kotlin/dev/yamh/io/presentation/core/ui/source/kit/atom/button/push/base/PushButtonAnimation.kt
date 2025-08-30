package dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.base

import androidx.compose.animation.core.Easing
import androidx.compose.runtime.Immutable

@Immutable
public interface PushButtonAnimation {
    public val duration: Int
    public val easing: Easing
}

public data class PushButtonAnimationConfigure(
    val duration: Int,
    val easing: Easing
) {
    public fun getButtonAnimation(): PushButtonAnimation {
        return object : PushButtonAnimation {
            override val duration: Int = this@PushButtonAnimationConfigure.duration
            override val easing: Easing = this@PushButtonAnimationConfigure.easing
        }
    }
}