package dev.yamh.io.presentation.feature.authorization.di

import dev.yamh.io.presentation.feature.authorization.authorization.AuthorizationViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

public val presentationFeatureAuthorization: Module = module {
    single<AuthorizationViewModel> { AuthorizationViewModel(get(), get()) }
}