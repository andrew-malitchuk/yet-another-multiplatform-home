package dev.yamh.io.presentation.feature.splash.splash

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SafeContainer
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashIntent
import dev.yamh.io.presentation.feature.splash.splash.view.SplashView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun SplashScreen() {

    val viewModel: SplashViewModel = getKoin().get<SplashViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val onboardingEffectHandler = SplashEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup = {
           viewModel.onIntent(SplashIntent.Setup)
        }
        onEffect = onboardingEffectHandler::proceedEffect
        render { state ->
            SafeContainer {
                SplashView()
            }
        }
    }

}
