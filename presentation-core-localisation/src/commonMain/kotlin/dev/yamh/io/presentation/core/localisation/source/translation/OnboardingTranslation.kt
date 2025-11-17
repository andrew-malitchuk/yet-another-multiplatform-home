package dev.yamh.io.presentation.core.localisation.source.translation

import dev.yamh.io.presentation.core.localisation.source.translation.base.BaseTranslation

public data class OnboardingTranslation(
    val welcome: String,
    val page1Title: String,
    val page1Description: String,
    val page2Title: String,
    val page2Description: String,
    val page3Title: String,
    val page3Description: String,
    val page4Title: String,
    val page4Description: String,
) : BaseTranslation