package dev.yamh.io.presentation.feature.device.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import dev.yamh.domain.core.source.model.device.GeneralDeviceType
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.device.source.contact.ContactScreen
import dev.yamh.io.presentation.feature.device.source.settings.SettingsScreen
import dev.yamh.io.presentation.feature.device.source.switcher.SwitcherScreen
import dev.yamh.io.presentation.feature.device.source.temperature.TemperatureScreen
import dev.yamh.io.presentation.feature.device.source.window.WindowScreen
import kotlinx.serialization.json.Json

context(NavHostController)
public fun NavGraphBuilder.deviceNavigationGraph() {
    navigation<GlobalNavigation.Device>(
        startDestination = DeviceInnerNavigationDirection.Switcher(),
    ) {
        composable<DeviceInnerNavigationDirection.Switcher>(
            enterTransition = {
                scaleIn(initialScale = 0.92f, animationSpec = tween(350)) + fadeIn(
                    tween(350)
                )
            },
            exitTransition = { fadeOut(tween(200)) },
            popEnterTransition = {
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(350)
                ) + fadeIn(tween(350))
            },
            popExitTransition = { fadeOut(tween(200)) }
        ) { backStackEntry ->
            val deviceEntity =
                Json.decodeFromString<DeviceEntity>(backStackEntry.toRoute<GlobalNavigation.Device>().device)

            when (deviceEntity.generalType()) {
                GeneralDeviceType.Light -> SwitcherScreen(
                    device = deviceEntity
                )

                GeneralDeviceType.Switch -> SwitcherScreen(
                    device = deviceEntity
                )

                GeneralDeviceType.Temperature -> TemperatureScreen(
                    device = deviceEntity
                )

                GeneralDeviceType.Contact -> ContactScreen(
                    device = deviceEntity
                )

                GeneralDeviceType.WindowCovering -> WindowScreen(
                    device = deviceEntity
                )

                else -> SwitcherScreen(
                    device = deviceEntity
                )
            }


        }
        composable<DeviceInnerNavigationDirection.Settings> {backStackEntry->
            val deviceEntity =
                Json.decodeFromString<DeviceEntity>(backStackEntry.toRoute<GlobalNavigation.Device>().device)
            SettingsScreen(
                device = deviceEntity
            )
        }
    }
}
