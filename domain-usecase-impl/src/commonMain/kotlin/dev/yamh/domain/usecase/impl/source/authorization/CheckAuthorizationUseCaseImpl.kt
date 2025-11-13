package dev.yamh.domain.usecase.impl.source.authorization

import dev.yamh.domain.repository.source.AuthorizationRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.authorization.AuthorizeUseCase
import dev.yamh.domain.usecase.source.authorization.CheckAuthorizationUseCase


public class CheckAuthorizationUseCaseImpl(
    private val authorizationRepository: AuthorizationRepository
) : CheckAuthorizationUseCase {
    override suspend fun invoke(): Result<Boolean> = resultLauncher {
        authorizationRepository.isAuthorized()
    }
}