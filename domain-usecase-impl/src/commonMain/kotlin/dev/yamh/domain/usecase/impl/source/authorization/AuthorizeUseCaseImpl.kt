package dev.yamh.domain.usecase.impl.source.authorization

import dev.yamh.domain.repository.source.AuthorizationRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.authorization.AuthorizeUseCase

public class AuthorizeUseCaseImpl(
    private val authorizationRepository: AuthorizationRepository
) : AuthorizeUseCase {
    public override suspend operator fun invoke(): Result<Boolean> = resultLauncher {
        authorizationRepository.requestAuthorization()
    }
}