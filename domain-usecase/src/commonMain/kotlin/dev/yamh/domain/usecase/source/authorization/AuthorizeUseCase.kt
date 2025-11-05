package dev.yamh.domain.usecase.source.authorization

import dev.yamh.domain.usecase.core.monad.Optional

public interface AuthorizeUseCase {
    public suspend operator fun invoke(): Result<Boolean>
}