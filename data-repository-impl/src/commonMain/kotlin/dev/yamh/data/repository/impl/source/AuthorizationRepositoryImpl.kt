package dev.yamh.data.repository.impl.source

import dev.yamh.domain.repository.source.AuthorizationRepository
import dev.yamh.io.data.ghome.source.datasource.authorization.AuthorizationHelper
import kotlin.getValue
import org.koin.core.component.inject


public class AuthorizationRepositoryImpl() : AuthorizationRepository,
    org.koin.core.component.KoinComponent {

    public val authorizationHelper: AuthorizationHelper by inject()

    override suspend fun isAuthorized(): Boolean {
        return authorizationHelper.isAuthorized()
    }

    override suspend fun requestAuthorization(): Boolean {
        return authorizationHelper.requestAuthorization()
    }

}