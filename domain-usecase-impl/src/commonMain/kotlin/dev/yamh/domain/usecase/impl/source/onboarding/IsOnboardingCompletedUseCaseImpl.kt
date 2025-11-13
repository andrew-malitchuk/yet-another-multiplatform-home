package dev.yamh.domain.usecase.impl.source.onboarding

import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.onboarding.IsOnboardingCompletedUseCase
import dev.yamh.domain.usecase.source.onboarding.SetOnboardingCompletedUseCase

public class IsOnboardingCompletedUseCaseImpl(
    private val settingsRepository: SettingsRepository
) : IsOnboardingCompletedUseCase {
    override suspend fun invoke(): Result<Boolean> = resultLauncher {
        settingsRepository.isOnboardingCompleted() ?: false
    }
}