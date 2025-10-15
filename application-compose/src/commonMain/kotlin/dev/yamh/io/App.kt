package dev.yamh.io

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.source.SubscribeToLanguageChangesUseCase
import dev.yamh.domain.usecase.source.SubscribeToThemeChangesUseCase
import dev.yamh.io.data.ghome.source.datasource.HomeClientModel
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.authorization.navigation.authorizationNavigationGraph
import dev.yamh.io.presentation.feature.device.navigation.deviceNavigationGraph
import dev.yamh.io.presentation.feature.homes.navigation.homesNavigationGraph
import dev.yamh.io.presentation.feature.main.navigation.mainNavigationGraph
import dev.yamh.io.presentation.feature.onboarding.navigation.onboardingNavigationGraph
import dev.yamh.io.presentation.feature.room.navigation.roomNavigationGraph
import dev.yamh.io.presentation.feature.settings.navigation.settingsNavigationGraph
import dev.yamh.io.presentation.feature.splash.navigation.splashNavigationGraph
import dev.yamh.presentation.core.styling.source.theme.AppTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.getKoin

@Composable
@Preview
public fun App(
    device: String? = null,
) {

    val settingsRepository: SettingsRepository = getKoin().get()
    val subscribeToThemeChangesUseCase: SubscribeToThemeChangesUseCase = getKoin().get()
    val subscribeToLanguageChangesUseCase: SubscribeToLanguageChangesUseCase = getKoin().get()

    val scope = rememberCoroutineScope()
    scope.launch {
        settingsRepository.getLanguage()
        settingsRepository.isDarkTheme()
    }

    val language = subscribeToLanguageChangesUseCase().collectAsStateWithLifecycle(
        initialValue = Result.success(LanguageEntity.ENGLISH)
    )
    val themeFlow = subscribeToThemeChangesUseCase().collectAsStateWithLifecycle(
        initialValue = Result.success(ThemeEntity.DARK)
    )

    AppTheme(
        useDarkTheme = themeFlow.value.getOrNull()?.isDark ?: true,
        language = (language.value.getOrNull() ?: LanguageEntity.ENGLISH).lang,
    ) {
        val startDestination = when {
            device != null -> GlobalNavigation.Device(device = device)
            else -> GlobalNavigation.Splash
        }

        HostNavigationGraph(
            navHostController = rememberNavController(),
            startDestination = startDestination,
        )
    }
}


@Composable
public fun HostNavigationGraph(
    navHostController: NavHostController,
    startDestination: Any,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
        with(navHostController) {
            splashNavigationGraph()
            onboardingNavigationGraph()
            authorizationNavigationGraph()
            homesNavigationGraph()
            mainNavigationGraph()
            roomNavigationGraph()
            deviceNavigationGraph()
            settingsNavigationGraph()
        }
    }
}