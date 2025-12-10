package dev.yamh.io.presentation.core.ui.source.kit.atom.divider

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun HorizontalAnimatedDivider(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
) {

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
    )

    Divider(
        modifier = modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .alpha(alpha),
        thickness = 4.dp,
        color = Theme.color.secondary1
    )

}