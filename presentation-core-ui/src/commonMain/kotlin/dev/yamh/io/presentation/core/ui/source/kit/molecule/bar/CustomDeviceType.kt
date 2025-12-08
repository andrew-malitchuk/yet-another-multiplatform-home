package dev.yamh.io.presentation.core.ui.source.kit.molecule.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.push.PushButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Droplet32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Lightning32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Lock32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.LogIn24
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Radio32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Sun32
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun CustomDeviceType(
    modifier: Modifier = Modifier,
    state: DeviceType,
    onAction: (type: DeviceType) -> Unit = {}
) {
    Row(
        modifier = modifier,
    ) {
        PushButton(
            icon = Droplet32,
            isSelected = state == DeviceType.Water,
        ) {
            onAction(DeviceType.Water)
        }
        Spacer(modifier = Modifier.width(Theme.spacing.space16))
        PushButton(
            icon = Lock32,
            isSelected = state == DeviceType.Door,
        ) {
            onAction(DeviceType.Door)
        }
        Spacer(modifier = Modifier.width(Theme.spacing.space16))
        PushButton(
            icon = Radio32,
            isSelected = state == DeviceType.Move,
        ) {
            onAction(DeviceType.Move)
        }
    }
}

public enum class DeviceType(public val typeName: String) {
    Water("Water"),
    Door("Door"),
    Move("Move"),
}