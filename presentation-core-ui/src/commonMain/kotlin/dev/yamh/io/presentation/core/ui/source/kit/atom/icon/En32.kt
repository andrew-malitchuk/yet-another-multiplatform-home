package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val En32: ImageVector
    get() {
        if (_En32 != null) {
            return _En32!!
        }
        _En32 = ImageVector.Builder(
            name = "EN",
            defaultWidth = 28.dp,
            defaultHeight = 18.dp,
            viewportWidth = 28f,
            viewportHeight = 18f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(9.883f, 18f)
                horizontalLineTo(0.344f)
                verticalLineTo(0.867f)
                horizontalLineTo(9.883f)
                verticalLineTo(2.625f)
                horizontalLineTo(2.336f)
                verticalLineTo(8.168f)
                horizontalLineTo(9.449f)
                verticalLineTo(9.902f)
                horizontalLineTo(2.336f)
                verticalLineTo(16.242f)
                horizontalLineTo(9.883f)
                verticalLineTo(18f)
                close()
                moveTo(27.098f, 18f)
                horizontalLineTo(24.801f)
                lineTo(15.462f, 3.609f)
                horizontalLineTo(15.368f)
                curveTo(15.391f, 3.992f, 15.418f, 4.43f, 15.45f, 4.922f)
                curveTo(15.481f, 5.406f, 15.505f, 5.926f, 15.52f, 6.48f)
                curveTo(15.543f, 7.027f, 15.555f, 7.586f, 15.555f, 8.156f)
                verticalLineTo(18f)
                horizontalLineTo(13.704f)
                verticalLineTo(0.867f)
                horizontalLineTo(15.989f)
                lineTo(25.294f, 15.211f)
                horizontalLineTo(25.376f)
                curveTo(25.36f, 14.938f, 25.34f, 14.551f, 25.317f, 14.051f)
                curveTo(25.294f, 13.543f, 25.27f, 13f, 25.247f, 12.422f)
                curveTo(25.231f, 11.836f, 25.223f, 11.293f, 25.223f, 10.793f)
                verticalLineTo(0.867f)
                horizontalLineTo(27.098f)
                verticalLineTo(18f)
                close()
            }
        }.build()

        return _En32!!
    }

@Suppress("ObjectPropertyName")
private var _En32: ImageVector? = null
