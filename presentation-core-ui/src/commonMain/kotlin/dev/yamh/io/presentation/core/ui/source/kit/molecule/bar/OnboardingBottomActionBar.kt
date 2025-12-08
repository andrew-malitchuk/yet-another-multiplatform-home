package dev.yamh.io.presentation.core.ui.source.kit.molecule.bar

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.yamh.io.presentation.core.ui.source.kit.atom.button.primary.PrimaryIconButton
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.ArrowRight32
import dev.yamh.io.presentation.core.ui.source.kit.atom.icon.Check32
import dev.yamh.presentation.core.styling.core.Theme

@Composable
public fun OnboardingBottomActionBar(
    modifier: Modifier,
    done: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Theme.spacing.space16),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Crossfade(targetState = done, label = "IconCrossfade") { isDone ->
            Box(modifier= Modifier){
                PrimaryIconButton(
                    modifier = Modifier,
                    icon = when (done) {
                        false -> ArrowRight32
                        true -> Check32
                    },
                    onClick = onClick
                )
            }
        }
    }
}