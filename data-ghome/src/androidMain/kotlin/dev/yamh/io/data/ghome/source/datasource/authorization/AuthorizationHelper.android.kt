package dev.yamh.io.data.ghome.source.datasource.authorization

import com.google.home.HomeClient
import com.google.home.HomeException
import com.google.home.PermissionsResult
import com.google.home.PermissionsResultStatus
import com.google.home.PermissionsState
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

public actual class AuthorizationHelper : KoinComponent {

    public actual constructor()

    public val nativeHomeClient: HomeClient by inject()

    public actual suspend fun requestAuthorization(): Boolean {
        val result =
            try {
                nativeHomeClient.requestPermissions()
            } catch (e: HomeException) {
                PermissionsResult(
                    PermissionsResultStatus.ERROR,
                    "Got HomeException with error: ${e.message}",
                )
            }
        return when (result.status) {
            PermissionsResultStatus.SUCCESS -> true
            PermissionsResultStatus.CANCELLED -> false
            else -> false
        }
    }

    public actual suspend fun isAuthorized(): Boolean {
        val result = nativeHomeClient.hasPermissions().firstOrNull()

        return when (result) {
            PermissionsState.GRANTED -> true
            PermissionsState.PERMISSIONS_STATE_UNAVAILABLE -> false
            PermissionsState.NOT_GRANTED -> false
            PermissionsState.PERMISSIONS_STATE_UNINITIALIZED -> true
            else -> false
        }
    }

}