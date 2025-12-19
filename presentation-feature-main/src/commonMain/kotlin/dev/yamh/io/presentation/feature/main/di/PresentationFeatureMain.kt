package dev.yamh.io.presentation.feature.main.di

import dev.yamh.io.presentation.feature.main.main.MainViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

public val presentationFeatureMain: Module = module {
    single<MainViewModel> { MainViewModel(get(),get(),get()) }
}