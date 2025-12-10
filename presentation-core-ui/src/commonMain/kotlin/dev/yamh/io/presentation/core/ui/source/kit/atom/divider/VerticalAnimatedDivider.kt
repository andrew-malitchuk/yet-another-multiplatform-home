package dev.yamh.io.presentation.core.ui.source.kit.atom.divider

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun VerticalAnimatedDivider(
    modifier: Modifier = Modifier,
    isVisible: Boolean,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = isVisible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .clip(CircleShape),
            thickness = 4.dp,
            color = Theme.color.secondary1
        )
    }

}