package dev.yamh.io.presentation.core.ui.source.kit.atom.loading

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import dev.yamh.io.presentation.core.ui.core.ext.fractionTransition
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin


@Composable
public fun LoadingDots(
    modifier: Modifier = Modifier,
    durationMillis: Int = 2_500,
    durationBetweenDotsMillis: Int = 500,
    color: Pair<Color, Color>
) {
    BoxWithConstraints {

        val multiplier = this.maxWidth / 2

        val size = DpSize(multiplier, multiplier)

        val transition = rememberInfiniteTransition(label = "")

        val dotPathMultiplier1 = transition.fractionTransition(
            initialValue = 0f,
            targetValue = 1f,
            fraction = 2,
            durationMillis = durationMillis,
            easing = LinearEasing
        )
        val dotPathMultiplier2 = transition.fractionTransition(
            initialValue = 0f,
            targetValue = 1f,
            fraction = 2,
            durationMillis = durationMillis,
            offsetMillis = durationBetweenDotsMillis,
            easing = LinearEasing
        )

        val circleRadiusMultiplier1 = transition.fractionTransition(
            initialValue = 0f,
            targetValue = 1f,
            durationMillis = durationMillis / 2,
            repeatMode = RepeatMode.Reverse,
            easing = EaseInOut
        )
        val circleRadiusMultiplier2 = transition.fractionTransition(
            initialValue = 0f,
            targetValue = 1f,
            durationMillis = durationMillis / 2,
            repeatMode = RepeatMode.Reverse,
            offsetMillis = durationMillis / 2,
            easing = EaseInOut
        )

        Canvas(modifier = modifier.size(size)) {

            val radius = (this.size.height / 2)
            val radiusCommon = this.size.height / 3

            val radius1 = circleRadiusMultiplier1.value * radiusCommon
            val angle1 = (dotPathMultiplier1.value * 360.0)
            val offsetX1 = -(radius * sin(toRadians(angle1))).toFloat() + (this.size.width / 2)
            val offsetY1 = (radius * cos(toRadians(angle1))).toFloat() + (this.size.height / 2)
            drawCircle(
                color = color.first,
                radius = radius1,
                center = Offset(offsetX1, offsetY1)
            )

            val radius2 = circleRadiusMultiplier2.value * radiusCommon
            val angle2 = (dotPathMultiplier2.value * 360.0)
            val offsetX2 = -(radius * sin(toRadians(angle2))).toFloat() + (this.size.width / 2)
            val offsetY2 = (radius * cos(toRadians(angle2))).toFloat() + (this.size.height / 2)
            drawCircle(
                color = color.second,
                radius = radius2,
                center = Offset(offsetX2, offsetY2)
            )
        }
    }
}

private fun toRadians(degrees: Double): Double = degrees * (PI / 180.0)