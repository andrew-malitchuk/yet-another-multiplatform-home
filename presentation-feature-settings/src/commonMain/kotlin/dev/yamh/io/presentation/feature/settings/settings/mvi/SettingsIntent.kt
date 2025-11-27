package dev.yamh.io.presentation.feature.settings.settings.mvi

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class SettingsIntent : MviIntent {
    public data object Setup : SettingsIntent()
    public data object OnBackClickIntent : SettingsIntent()
    public data class OnLanguageChangeIntent(val lang: LanguageEntity) : SettingsIntent()
    public data class OnThemeChangeIntent(val theme: ThemeEntity) : SettingsIntent()
    public data object OnGoToGithubIntent : SettingsIntent()
    public data object OnGoToLinkedInIntent : SettingsIntent()
}