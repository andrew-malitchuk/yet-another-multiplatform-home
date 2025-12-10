import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val ArrowDown32: ImageVector
    get() {
        if (_ArrowDown32 != null) {
            return _ArrowDown32!!
        }
        _ArrowDown32 = ImageVector.Builder(
            name = "ArrowDown",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                rotate = 180f,
                pivotX = 12f,
                pivotY = 12f
            ) {
                path(
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(12f, 19f)
                    lineTo(12f, 5f)
                }
                path(
                    stroke = SolidColor(Color.Black),
                    strokeLineWidth = 2f,
                    strokeLineCap = StrokeCap.Round,
                    strokeLineJoin = StrokeJoin.Round
                ) {
                    moveTo(5f, 12f)
                    lineToRelative(7f, -7f)
                    lineToRelative(7f, 7f)
                }
            }
        }.build()

        return _ArrowDown32!!
    }

@Suppress("ObjectPropertyName")
private var _ArrowDown32: ImageVector? = null