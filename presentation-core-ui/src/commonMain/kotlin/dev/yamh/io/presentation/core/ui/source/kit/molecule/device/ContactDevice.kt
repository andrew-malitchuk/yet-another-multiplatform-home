package dev.yamh.io.presentation.core.ui.source.kit.molecule.device

import androidx.annotation.Size
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SimpleRoundContainer
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.X32

import androidx.compose.runtime.Stable
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.DefaultTintColor
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import dev.yamh.common.core.core.Color
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Droplet32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Lock32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Morph
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Radio32
import dev.yamh.presentation.core.styling.core.Theme
import kotlin.math.*

@Composable
public fun ContactDevice(
    modifier: Modifier = Modifier,
    cancelled: Boolean,
    state: dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType?
) {

    val icon =
        when (state) {
            dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType.Water -> Droplet32
            dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType.Door -> Lock32
            dev.yamh.io.presentation.core.ui.source.kit.molecule.bar.DeviceType.Move -> Radio32
            null -> Droplet32
        }


    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = rememberVectorPainter(Morph),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Theme.color.primary1),
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillBounds
        )
        Icon(
            modifier = Modifier
                .size(24.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Theme.color.primary0,
        )
        AnimatedVisibility(
            visible = cancelled
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = X32,
                contentDescription = null,
                tint = Theme.color.primary0,
            )
        }
    }
}



