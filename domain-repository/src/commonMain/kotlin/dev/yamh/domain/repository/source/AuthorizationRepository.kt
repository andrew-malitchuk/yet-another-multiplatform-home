package dev.yamh.domain.repository.source

public interface AuthorizationRepository {
    public suspend fun isAuthorized(): Boolean
    public suspend fun requestAuthorization(): Boolean
}