package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val ArrowRight32: ImageVector
    get() {
        if (_ArrowRight32 != null) {
            return _ArrowRight32!!
        }
        _ArrowRight32 = ImageVector.Builder(
            name = "IconArrowLeft32",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 32f,
            viewportHeight = 32f
        ).apply {
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(6.667f, 16f)
                horizontalLineTo(25.333f)
            }
            path(
                stroke = SolidColor(Color(0xFFFFFFFF)),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 6.667f)
                lineTo(25.333f, 16f)
                lineTo(16f, 25.333f)
            }
        }.build()

        return _ArrowRight32!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowRight32: ImageVector? = null
