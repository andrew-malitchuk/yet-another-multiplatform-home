package dev.yamh.io.presentation.core.ui.source.kit.atom.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val MouseScrollDown32: ImageVector
    get() {
        if (_MouseScrollDown32 != null) {
            return _MouseScrollDown32!!
        }
_MouseScrollDown32 = ImageVector.Builder(
    name = "MouseScrollDown32",
    defaultWidth = 32.dp,
    defaultHeight = 32.dp,
    viewportWidth = 256f,
    viewportHeight = 256f
).apply {
    path(
        fill = SolidColor(Color.Black)
    ) {
        // 180 degree rotation: (x, y) -> (256 - x, 256 - y)
        moveTo(112f, 240f)
        lineTo(144f, 240f)
        arcTo(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, 208f, 176f)
        verticalLineToRelative(-96f)
        arcToRelative(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, -64f, -64f)
        horizontalLineToRelative(-32f)
        arcToRelative(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, -64f, 64f)
        lineTo(48f, 176f)
        arcTo(64.07f, 64.07f, 0f, isMoreThanHalf = false, isPositiveArc = false, 112f, 240f)
        close()
        moveTo(64f, 80f)
        arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, 48f, -48f)
        lineTo(144f, 32f)
        arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, 48f, 48f)
        lineTo(192f, 176f)
        arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, -48f, 48f)
        horizontalLineToRelative(-32f)
        arcToRelative(48.05f, 48.05f, 0f, isMoreThanHalf = false, isPositiveArc = true, -48f, -48f)
        close()
        moveTo(120f, 192f)
        verticalLineToRelative(-48f)
        arcToRelative(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 0f)
        lineTo(136f, 192f)
        arcToRelative(8f, 8f, 0f, isMoreThanHalf = false, isPositiveArc = true, -16f, 0f)
        close()
    }
}.build()

        return _MouseScrollDown32!!
    }

@Suppress("ObjectPropertyName")
private var _MouseScrollDown32: ImageVector? = null
