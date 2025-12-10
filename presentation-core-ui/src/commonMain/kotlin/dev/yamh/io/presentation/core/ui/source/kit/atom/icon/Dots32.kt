package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Dots32: ImageVector
    get() {
        if (_Dots32 != null) {
            return _Dots32!!
        }
        _Dots32 = ImageVector.Builder(
            name = "Dots",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(9f, 16f)
                curveTo(9f, 16.265f, 8.895f, 16.52f, 8.707f, 16.707f)
                curveTo(8.52f, 16.895f, 8.265f, 17f, 8f, 17f)
                curveTo(7.735f, 17f, 7.48f, 16.895f, 7.293f, 16.707f)
                curveTo(7.105f, 16.52f, 7f, 16.265f, 7f, 16f)
                curveTo(7f, 15.735f, 7.105f, 15.48f, 7.293f, 15.293f)
                curveTo(7.48f, 15.105f, 7.735f, 15f, 8f, 15f)
                curveTo(8.265f, 15f, 8.52f, 15.105f, 8.707f, 15.293f)
                curveTo(8.895f, 15.48f, 9f, 15.735f, 9f, 16f)
                close()
                moveTo(17f, 16f)
                curveTo(17f, 16.265f, 16.895f, 16.52f, 16.707f, 16.707f)
                curveTo(16.52f, 16.895f, 16.265f, 17f, 16f, 17f)
                curveTo(15.735f, 17f, 15.48f, 16.895f, 15.293f, 16.707f)
                curveTo(15.105f, 16.52f, 15f, 16.265f, 15f, 16f)
                curveTo(15f, 15.735f, 15.105f, 15.48f, 15.293f, 15.293f)
                curveTo(15.48f, 15.105f, 15.735f, 15f, 16f, 15f)
                curveTo(16.265f, 15f, 16.52f, 15.105f, 16.707f, 15.293f)
                curveTo(16.895f, 15.48f, 17f, 15.735f, 17f, 16f)
                close()
                moveTo(25f, 16f)
                curveTo(25f, 16.265f, 24.895f, 16.52f, 24.707f, 16.707f)
                curveTo(24.52f, 16.895f, 24.265f, 17f, 24f, 17f)
                curveTo(23.735f, 17f, 23.48f, 16.895f, 23.293f, 16.707f)
                curveTo(23.105f, 16.52f, 23f, 16.265f, 23f, 16f)
                curveTo(23f, 15.735f, 23.105f, 15.48f, 23.293f, 15.293f)
                curveTo(23.48f, 15.105f, 23.735f, 15f, 24f, 15f)
                curveTo(24.265f, 15f, 24.52f, 15.105f, 24.707f, 15.293f)
                curveTo(24.895f, 15.48f, 25f, 15.735f, 25f, 16f)
                close()
            }
        }.build()

        return _Dots32!!
    }

@Suppress("ObjectPropertyName")
private var _Dots32: ImageVector? = null
