package dev.yamh.io.presentation.feature.onboarding.di

import dev.yamh.io.presentation.feature.onboarding.onboarding.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

public val presentationFeatureOnboarding: Module = module {
    single<OnboardingViewModel> { OnboardingViewModel() }
}