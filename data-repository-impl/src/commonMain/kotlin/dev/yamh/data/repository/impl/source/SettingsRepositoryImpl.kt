package dev.yamh.data.repository.impl.source

import dev.yamh.data.preference.source.SettingsPreferenceSource
import dev.yamh.domain.repository.source.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.stateIn

public class SettingsRepositoryImpl(
    private val settingsPreferenceSource: SettingsPreferenceSource,
) : SettingsRepository {

    override val languageFlow: Flow<String> = settingsPreferenceSource.languageFlow.stateIn(
        scope = kotlinx.coroutines.GlobalScope,
        started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
        initialValue = "",
    )
    override val themeFlow: Flow<Boolean> = settingsPreferenceSource.themeFlow.stateIn(
        scope = kotlinx.coroutines.GlobalScope,
        started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
        initialValue = false,
    )

    override suspend fun getLanguage(): String? {
        return settingsPreferenceSource.getLanguage()
    }

    override suspend fun setLanguage(value: String) {
        settingsPreferenceSource.setLanguage(value)
    }

    override suspend fun isDarkTheme(): Boolean? {
        return settingsPreferenceSource.isDarkTheme()
    }

    override suspend fun setDarkTheme(value: Boolean) {
        return settingsPreferenceSource.setDarkTheme(value)
    }

    override suspend fun isOnboardingCompleted(): Boolean? {
        return settingsPreferenceSource.isOnboardingCompleted()
    }

    override suspend fun setOnboardingCompleted(value: Boolean) {
        settingsPreferenceSource.setOnboardingCompleted(value)
    }

}