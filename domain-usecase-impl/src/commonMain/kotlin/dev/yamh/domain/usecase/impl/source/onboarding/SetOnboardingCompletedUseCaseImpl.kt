package dev.yamh.domain.usecase.impl.source.onboarding

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.SetLanguageUseCase
import dev.yamh.domain.usecase.source.onboarding.SetOnboardingCompletedUseCase

public class SetOnboardingCompletedUseCaseImpl(
    private val settingsRepository: SettingsRepository
) : SetOnboardingCompletedUseCase {
    override suspend fun invoke(isCompleted: Boolean): Optional = resultLauncher{
        settingsRepository.setOnboardingCompleted(isCompleted)
    }
}