package dev.yamh.data.preference.impl.di

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.SuspendSettings
import com.russhwolf.settings.coroutines.toSuspendSettings
import org.koin.core.module.Module
import org.koin.dsl.module

public actual val dataPreferenceImplModule: Module = module {
    single<SuspendSettings> {
        KeychainSettings().toSuspendSettings()
    }
}