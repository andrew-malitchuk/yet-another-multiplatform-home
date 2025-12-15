package dev.yamh.io.presentation.feature.homes.di

import dev.yamh.io.presentation.feature.homes.homes.HomesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

public val presentationFeatureHomes: Module = module {
    single<HomesViewModel> { HomesViewModel(
        get(),
        get(),
        get(),
    ) }
}