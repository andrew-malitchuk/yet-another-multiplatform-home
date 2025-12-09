package dev.yamh.io.presentation.core.ui.source.kit.atom.progress

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public fun SegmentedPageIndicator(
    page: Int,
    totalPages: Int,
    modifier: Modifier = Modifier.size(80.dp).background(Color.Cyan),
    activeColor: Color = Color(0xFF3DDC84),
    inactiveColor: Color = Color(0xFFB0B0B0),
    strokeWidth: Dp = 8.dp,
    gapAngle: Float = 6f
) {
    Canvas(modifier = modifier) {
        val sweepPerSegment = (360f / totalPages) - gapAngle
        val startAngleOffset = -90f
        val radius = size.minDimension / 2
        val stroke = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)

        for (i in 0 until totalPages) {
            val startAngle = startAngleOffset + i * (sweepPerSegment + gapAngle)
            val color = if (i == page) activeColor else inactiveColor

            drawArc(
                color = color,
                startAngle = startAngle,
                sweepAngle = sweepPerSegment,
                useCenter = false,
                style = stroke,
                topLeft = Offset(
                    (size.width - radius * 2) / 2,
                    (size.height - radius * 2) / 2
                ),
                size = Size(radius * 2, radius * 2)
            )
        }
    }
}
