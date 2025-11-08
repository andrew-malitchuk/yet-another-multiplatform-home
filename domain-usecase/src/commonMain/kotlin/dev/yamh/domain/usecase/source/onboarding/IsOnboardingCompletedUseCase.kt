package dev.yamh.domain.usecase.source.onboarding

public interface IsOnboardingCompletedUseCase {
    public suspend operator fun invoke():Result<Boolean>
}