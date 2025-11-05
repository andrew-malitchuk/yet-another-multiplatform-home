package dev.yamh.domain.usecase.source.authorization

public interface CheckAuthorizationUseCase {
    public suspend operator fun invoke(): Result<Boolean>

}