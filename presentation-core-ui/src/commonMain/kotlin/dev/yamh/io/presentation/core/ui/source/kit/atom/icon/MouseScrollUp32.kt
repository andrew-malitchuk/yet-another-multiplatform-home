package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MouseScrollUp32: ImageVector
    get() {
        if (_MouseScrollUp32 != null) {
            return _MouseScrollUp32!!
        }
        _MouseScrollUp32 = ImageVector.Builder(
            name = "MouseScrollUp32",
            defaultWidth = 32.dp,
            defaultHeight = 32.dp,
            viewportWidth = 256f,
            viewportHeight = 256f
        ).apply {
            path(fill = SolidColor(Color.Black)) {
                moveTo(144f, 16f)
                lineTo(112f, 16f)
                arcTo(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, 48f, 80f)
                verticalLineToRelative(96f)
                arcToRelative(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, 64f, 64f)
                horizontalLineToRelative(32f)
                arcToRelative(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, 64f, -64f)
                lineTo(208f, 80f)
                arcTo(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, 144f, 16f)
                close()
                moveTo(192f, 176f)
                arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, -48f, 48f)
                lineTo(112f, 224f)
                arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, -48f, -48f)
                lineTo(64f, 80f)
                arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, 48f, -48f)
                horizontalLineToRelative(32f)
                arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, 48f, 48f)
                close()
                moveTo(136f, 64f)
                verticalLineToRelative(48f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, -16f, 0f)
                lineTo(120f, 64f)
                arcToRelative(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 0f)
                close()
            }
        }.build()

        return _MouseScrollUp32!!
    }

@Suppress("ObjectPropertyName")
private var _MouseScrollUp32: ImageVector? = null
