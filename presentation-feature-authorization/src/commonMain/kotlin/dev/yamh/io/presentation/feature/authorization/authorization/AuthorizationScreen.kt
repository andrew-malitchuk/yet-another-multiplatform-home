package dev.yamh.io.presentation.feature.authorization.authorization

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yamh.io.presentation.core.navigation.source.navigation.GlobalNavigation
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationIntent
import dev.yamh.io.presentation.feature.authorization.authorization.view.AuthorizationCompactView
import dev.yamh.io.presentation.feature.authorization.authorization.view.AuthorizationExpandedView
import dev.yamh.io.presentation.feature.authorization.authorization.view.AuthorizationFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun AuthorizationScreen() {
    val viewModel: AuthorizationViewModel = getKoin().get<AuthorizationViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val effectHandler = AuthorizationEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        onEffect = effectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    AuthorizationCompactView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    AuthorizationExpandedView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    AuthorizationExpandedView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    AuthorizationFoldVerticalView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
