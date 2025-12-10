package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Lightning32: ImageVector
    get() {
        if (_Lightning32 != null) {
            return _Lightning32!!
        }
        _Lightning32 = ImageVector.Builder(
            name = "Lightning32",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 1.5f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(5f, 18f)
                lineTo(19f, 3f)
                lineTo(16f, 14f)
                horizontalLineTo(27f)
                lineTo(13f, 29f)
                lineTo(16f, 18f)
                horizontalLineTo(5f)
                close()
            }
        }.build()

        return _Lightning32!!
    }

@Suppress("ObjectPropertyName")
private var _Lightning32: ImageVector? = null
