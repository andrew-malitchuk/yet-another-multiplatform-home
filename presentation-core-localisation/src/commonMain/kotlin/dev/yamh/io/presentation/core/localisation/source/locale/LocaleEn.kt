package dev.yamh.io.presentation.core.localisation.source.locale

import androidx.compose.ui.text.intl.Locale
import dev.yamh.io.presentation.core.localisation.source.Localisation
import dev.yamh.io.presentation.core.localisation.source.translation.AuthorizationTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.GeneralTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.OnboardingTranslation
import dev.yamh.io.presentation.core.localisation.source.translation.SettingsTranslation

internal val localeEn: Localisation = Localisation(
    onboarding = OnboardingTranslation(
        welcome = "Control your world. Anywhere.",
        page1Title = "Connect devices",
        page1Description = "Link your smart bulbs, sensors, and plugs in seconds.",
        page2Title = "Multiplatform Support",
        page2Description = "Use it on your phone, tablet, or desktop. Your choice.",
        page3Title = "Control effortlessly",
        page3Description = "Turn on the lights, set the temperature, or close the blinds — all from one screen.",
        page4Title = "All in one place",
        page4Description = "Manage everything. One app, all rooms, every device. Simple as that."
    ),
    authorization = AuthorizationTranslation(
        title = "Authorization Required",
        description = "Please authorize the app to access your Google Home devices.",
    ),
    settings = SettingsTranslation(
        biometry = "Biometry",
        enabled = "Enabled",
        language = "Language",
        theme = "Theme",
        about = "About",
        aboutDescription = "Yet Another Multiplatform Home",
        information = "Information",
        type = "Type",
        customize = "Customize",
        selected = "Selected",
    ),
    general = GeneralTranslation(
        empty = "No item is selected.",
        error = "Error",
        unknownError = "An unknown error occurred.",
        networkError = "Please check your connection.",
        releaseToRefresh = "Release to refresh",
        refreshing = "Refreshing...",
        pullToRefresh = "Pull to refresh",
    )
)