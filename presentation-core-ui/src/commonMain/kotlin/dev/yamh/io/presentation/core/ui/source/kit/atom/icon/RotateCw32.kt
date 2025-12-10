import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val RotateCw32: ImageVector
    get() {
        if (_RotateCw32 != null) {
            return _RotateCw32!!
        }
        _RotateCw32 = ImageVector.Builder(
            name = "RotateCw32",
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
                moveTo(23f, 4f)
                lineToRelative(0f, 6f)
                lineToRelative(-6f, 0f)
            }
            path(
                stroke = SolidColor(Color.Black),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(20.49f, 15f)
                arcToRelative(9f, 9f, 0f, isMoreThanHalf = true, isPositiveArc = true, -2.12f, -9.36f)
                lineTo(23f, 10f)
            }
        }.build()

        return _RotateCw32!!
    }

@Suppress("ObjectPropertyName")
private var _RotateCw32: ImageVector? = null
