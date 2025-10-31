package dev.yamh.data.preference.impl.source

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import dev.yamh.data.preference.source.SettingsPreferenceSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

public class SettingsPreferenceSourceImpl @OptIn(ExperimentalSettingsApi::class) constructor(
    private val settings: SuspendSettings,
) : SettingsPreferenceSource {

    override val languageFlow: MutableStateFlow<String> = MutableStateFlow("")

    override val themeFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)


    override suspend fun getLanguage(): String? {
        return settings.getString(KEY_LANGUAGE, "").ifBlank { null }?.also {
            languageFlow.emit(it)
        }
    }

    override suspend fun setLanguage(value: String) {
        settings.putString(KEY_LANGUAGE, value)
        languageFlow.emit(value)
    }


    override suspend fun isDarkTheme(): Boolean? {
        return settings.getBoolean(KEY_THEME, false).also {
            themeFlow.emit(it)
        }
    }

    override suspend fun setDarkTheme(value: Boolean) {
        settings.putBoolean(KEY_THEME, value)
        themeFlow.emit(value)
    }

    override suspend fun isOnboardingCompleted(): Boolean? {
        return settings.getBoolean(KEY_ONBOARDING, false)
    }

    override suspend fun setOnboardingCompleted(value: Boolean) {
        settings.putBoolean(KEY_ONBOARDING, value)
    }

    public companion object {
        public const val KEY_LANGUAGE: String = "language"
        public const val KEY_THEME: String = "theme"
        public const val KEY_ONBOARDING: String = "onboarding"
    }

}