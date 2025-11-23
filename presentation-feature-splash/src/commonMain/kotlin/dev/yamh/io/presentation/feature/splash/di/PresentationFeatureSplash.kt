package dev.yamh.io.presentation.feature.splash.di

import dev.yamh.io.presentation.feature.splash.splash.SplashViewModel
import org.koin.core.module.Module
import org.koin.dsl.module


public val presentationFeatureSplash: Module = module {
    single<SplashViewModel> { SplashViewModel(get(), get(), get(), get()) }
}