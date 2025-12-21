package dev.yamh.io.presentation.feature.onboarding.onboarding

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.onboarding.onboarding.view.OnboardingCompactView
import dev.yamh.io.presentation.feature.onboarding.onboarding.view.OnboardingExpandedView
import dev.yamh.io.presentation.feature.onboarding.onboarding.view.OnboardingFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun OnboardingScreen() {

    val viewModel: OnboardingViewModel = getKoin().get<OnboardingViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val onboardingEffectHandler = OnboardingEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        onEffect = onboardingEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    OnboardingCompactView(
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    OnboardingExpandedView(
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    OnboardingExpandedView(
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    OnboardingFoldVerticalView(
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
