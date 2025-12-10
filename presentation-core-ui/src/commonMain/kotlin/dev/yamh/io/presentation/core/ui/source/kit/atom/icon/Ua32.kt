package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Ua32: ImageVector
    get() {
        if (_Ua32 != null) {
            return _Ua32!!
        }
        _Ua32 = ImageVector.Builder(
            name = "UA",
            defaultWidth = 31.dp,
            defaultHeight = 19.dp,
            viewportWidth = 31f,
            viewportHeight = 19f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(13.305f, 0.867f)
                verticalLineTo(11.953f)
                curveTo(13.305f, 13.164f, 13.059f, 14.242f, 12.566f, 15.188f)
                curveTo(12.082f, 16.133f, 11.348f, 16.879f, 10.363f, 17.426f)
                curveTo(9.379f, 17.965f, 8.145f, 18.234f, 6.66f, 18.234f)
                curveTo(4.543f, 18.234f, 2.93f, 17.66f, 1.82f, 16.512f)
                curveTo(0.719f, 15.363f, 0.168f, 13.828f, 0.168f, 11.906f)
                verticalLineTo(0.867f)
                horizontalLineTo(2.172f)
                verticalLineTo(11.965f)
                curveTo(2.172f, 13.418f, 2.555f, 14.539f, 3.32f, 15.328f)
                curveTo(4.094f, 16.117f, 5.246f, 16.512f, 6.777f, 16.512f)
                curveTo(7.824f, 16.512f, 8.68f, 16.324f, 9.344f, 15.949f)
                curveTo(10.016f, 15.566f, 10.512f, 15.035f, 10.832f, 14.356f)
                curveTo(11.16f, 13.668f, 11.324f, 12.875f, 11.324f, 11.977f)
                verticalLineTo(0.867f)
                horizontalLineTo(13.305f)
                close()
                moveTo(28.622f, 18f)
                lineTo(26.512f, 12.563f)
                horizontalLineTo(19.645f)
                lineTo(17.547f, 18f)
                horizontalLineTo(15.532f)
                lineTo(22.235f, 0.797f)
                horizontalLineTo(24.028f)
                lineTo(30.684f, 18f)
                horizontalLineTo(28.622f)
                close()
                moveTo(25.915f, 10.793f)
                lineTo(23.911f, 5.391f)
                curveTo(23.864f, 5.25f, 23.786f, 5.02f, 23.676f, 4.699f)
                curveTo(23.575f, 4.379f, 23.469f, 4.047f, 23.36f, 3.703f)
                curveTo(23.251f, 3.359f, 23.161f, 3.082f, 23.09f, 2.871f)
                curveTo(23.012f, 3.191f, 22.926f, 3.512f, 22.833f, 3.832f)
                curveTo(22.747f, 4.145f, 22.661f, 4.438f, 22.575f, 4.711f)
                curveTo(22.489f, 4.977f, 22.415f, 5.203f, 22.352f, 5.391f)
                lineTo(20.313f, 10.793f)
                horizontalLineTo(25.915f)
                close()
            }
        }.build()

        return _Ua32!!
    }

@Suppress("ObjectPropertyName")
private var _Ua32: ImageVector? = null
