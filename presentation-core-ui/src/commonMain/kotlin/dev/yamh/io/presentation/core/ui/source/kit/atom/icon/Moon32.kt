package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Moon32: ImageVector
    get() {
        if (_Moon32 != null) {
            return _Moon32!!
        }
        _Moon32 = ImageVector.Builder(
            name = "Moon32",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21f, 12.79f)
                arcTo(9f, 9f, 0f, isMoreThanHalf = true, isPositiveArc = true, 11.21f, 3f)
                arcTo(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = false, 21f, 12.79f)
                close()
            }
        }.build()

        return _Moon32!!
    }

@Suppress("ObjectPropertyName")
private var _Moon32: ImageVector? = null
