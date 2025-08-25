package dev.yamh.data.preference.source

public interface SettingsPreferenceSource {

    public val languageFlow: kotlinx.coroutines.flow.Flow<String>
    public val themeFlow: kotlinx.coroutines.flow.Flow<Boolean>

    public suspend fun getLanguage(): String?
    public suspend fun setLanguage(value: String)

    public suspend fun isDarkTheme(): Boolean?
    public suspend fun setDarkTheme(value: Boolean)

    public suspend fun isOnboardingCompleted(): Boolean?
    public suspend fun setOnboardingCompleted(value: Boolean)

}