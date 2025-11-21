package dev.yamh.presentation.core.styling.source.attribute

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dev.yamh.presentation.core.styling.core.ThemeTypography
import org.jetbrains.compose.resources.Font
import yet_another_multiplatform_home.presentation_core_styling.generated.resources.Res
import yet_another_multiplatform_home.presentation_core_styling.generated.resources.opensans_regular
import yet_another_multiplatform_home.presentation_core_styling.generated.resources.sora_regular
import yet_another_multiplatform_home.presentation_core_styling.generated.resources.sora_semibold

@Composable
public fun SoraFontFamily(): FontFamily = FontFamily(
    Font(
        resource = Res.font.sora_regular,
        weight = FontWeight.Black,
        style = FontStyle.Normal,
    ),
    Font(
        resource = Res.font.sora_semibold,
        weight = FontWeight.SemiBold,
        style = FontStyle.Normal,
    ),
)

@Composable
public fun OpenSansFontFamily(): FontFamily = FontFamily(
    Font(
        resource = Res.font.opensans_regular,
        weight = FontWeight.Black,
        style = FontStyle.Normal,
    ),
)

@Composable
public fun AttributeTypography(): ThemeTypography =
    ThemeTypography(
        display =
            TextStyle(
                fontSize = attributeFontSize.display,
                lineHeight = attributeLineHeight.display,
                fontWeight = FontWeight.Normal,
                fontFamily = SoraFontFamily()
            ),
        headline =
            TextStyle(
                fontSize = attributeFontSize.headline,
                lineHeight = attributeLineHeight.headline,
                fontWeight = FontWeight.Normal,
                fontFamily = SoraFontFamily()
            ),
        title =
            TextStyle(
                fontSize = attributeFontSize.title,
                lineHeight = attributeLineHeight.title,
                fontWeight = FontWeight.SemiBold,
                fontFamily = SoraFontFamily()
            ),
        subHeading =
            TextStyle(
                fontSize = attributeFontSize.subHeading,
                lineHeight = attributeLineHeight.subHeading,
                fontWeight = FontWeight.Normal,
                fontFamily = OpenSansFontFamily()
            ),
        body =
            TextStyle(
                fontSize = attributeFontSize.body,
                lineHeight = attributeLineHeight.body,
                fontWeight = FontWeight.Normal,
                fontFamily =
                    SoraFontFamily()
            ),
        caption =
            TextStyle(
                fontSize = attributeFontSize.caption,
                lineHeight = attributeLineHeight.caption,
                fontWeight = FontWeight.Normal,
                fontFamily = SoraFontFamily()
            ),
        button =
            TextStyle(
                fontSize = attributeFontSize.button,
                lineHeight = attributeLineHeight.button,
                fontFamily = OpenSansFontFamily()
            ),

        )