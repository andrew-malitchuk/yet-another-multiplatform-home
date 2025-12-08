package dev.yamh.io.presentation.core.ui.source.kit.molecule.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.SimpleCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.text.AutoSizeText
import dev.yamh.presentation.core.styling.core.Theme
import dev.yamh.presentation.core.styling.source.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun OnboardingCard(
    modifier: Modifier = Modifier,
    background: Color,
    title: String,
    description: String,
) {
    SimpleCard(
        modifier = modifier,
        background = background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            AutoSizeText(
                text = title,
                style = Theme.typography.headline,
                color = Theme.color.primary0,
                maxLines = 2,
                minLines = 2,
            )
            Spacer(modifier = Modifier.weight(1f))
            AutoSizeText(
                text = description,
                style = Theme.typography.body,
                color = Theme.color.primary0,
                maxLines = 2,
                minLines = 2
            )
        }
    }
}

@Composable
@Preview
private fun PreviewOnboardingCard() {
    AppTheme(
        useDarkTheme = false,
        language = "en",
    ) {
        OnboardingCard(
            background = Theme.color.accent1,
            title = "Welcome to the App",
            description = "Discover new features and functionalities designed to enhance your experience."
        )
    }
}