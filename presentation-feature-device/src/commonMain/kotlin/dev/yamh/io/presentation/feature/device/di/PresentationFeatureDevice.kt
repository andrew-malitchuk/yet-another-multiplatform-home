package dev.yamh.io.presentation.feature.device.di

import dev.yamh.io.presentation.feature.device.source.contact.ContactViewModel
import dev.yamh.io.presentation.feature.device.source.switcher.SwitcherViewModel
import dev.yamh.io.presentation.feature.device.source.settings.SettingsViewModel
import dev.yamh.io.presentation.feature.device.source.temperature.TemperatureViewModel
import dev.yamh.io.presentation.feature.device.source.window.WindowViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

public val presentationFeatureDevice: Module = module {
    single<SwitcherViewModel> { SwitcherViewModel(get(),get(),get()) }
    single<TemperatureViewModel> { TemperatureViewModel(get(),get()) }
    single<ContactViewModel> { ContactViewModel(get(),get()) }
    single<SettingsViewModel> { SettingsViewModel(get(),get(), get()) }
    single<WindowViewModel> { WindowViewModel(get(), get(), get()) }
}