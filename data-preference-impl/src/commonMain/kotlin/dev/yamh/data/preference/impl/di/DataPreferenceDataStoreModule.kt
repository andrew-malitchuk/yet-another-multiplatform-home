package dev.yamh.data.preference.impl.di

import dev.yamh.data.preference.impl.source.HomePreferenceSourceImpl
import dev.yamh.data.preference.impl.source.SettingsPreferenceSourceImpl
import dev.yamh.data.preference.source.HomePreferenceSource
import dev.yamh.data.preference.source.SettingsPreferenceSource
import org.koin.core.module.Module
import org.koin.dsl.module

public val dataPreferenceDataStoreModule: Module = module {
    single<SettingsPreferenceSource> {
        SettingsPreferenceSourceImpl(
            get(),
        )
    }
    single<HomePreferenceSource> {
        HomePreferenceSourceImpl(
            get(),
        )
    }
}