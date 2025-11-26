package dev.yamh.io.presentation.feature.settings.di

import dev.yamh.io.presentation.feature.settings.settings.SettingsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

public val presentationFeatureSettings: Module = module {
    single<SettingsViewModel> { SettingsViewModel(get(), get(), get(), get()) }
}