package dev.yamh.io.presentation.core.localisation.source

import dev.yamh.io.presentation.core.localisation.source.translation.AuthorizationTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.GeneralTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.OnboardingTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.SettingsTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.base.BaseTranslation
import kotlin.reflect.KProperty1

public data class Localisation(
    val onboarding: OnboardingTranslation,
    val authorization: AuthorizationTranslation,
    val settings: SettingsTranslation,
    val general: GeneralTranslation
)
