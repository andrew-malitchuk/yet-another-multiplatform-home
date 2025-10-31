package dev.yamh.data.preference.impl.source

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.SuspendSettings
import dev.yamh.data.preference.impl.source.SettingsPreferenceSourceImpl.Companion.KEY_LANGUAGE
import dev.yamh.data.preference.source.HomePreferenceSource
import dev.yamh.data.preference.source.SettingsPreferenceSource

public class HomePreferenceSourceImpl @OptIn(ExperimentalSettingsApi::class) constructor(
    private val settings: SuspendSettings,
) : HomePreferenceSource {
    override suspend fun getSelectedHome(): String? {
        return settings.getString(KEY_HOME, "").ifBlank { null }
    }

    override suspend fun setSelectedHome(value: String) {
        settings.putString(KEY_HOME, value)
    }

    public companion object {
        public const val KEY_HOME: String = "home"
    }
}