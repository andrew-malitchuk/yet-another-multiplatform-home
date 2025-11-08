package dev.yamh.domain.usecase.source.onboarding

import dev.yamh.domain.usecase.core.monad.Optional

public interface SetOnboardingCompletedUseCase {
    public suspend operator fun invoke(isCompleted: Boolean): Optional
}