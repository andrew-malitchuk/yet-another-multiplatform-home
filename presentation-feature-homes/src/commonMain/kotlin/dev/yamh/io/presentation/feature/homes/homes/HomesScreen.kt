package dev.yamh.io.presentation.feature.homes.homes

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesIntent
import dev.yamh.io.presentation.feature.homes.homes.view.HomesCompactView
import dev.yamh.io.presentation.feature.homes.homes.view.HomesExpandedView
import dev.yamh.io.presentation.feature.homes.homes.view.HomesFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController) public fun HomesScreen() {

    val viewModel: HomesViewModel = getKoin().get<HomesViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val homesEffectHandler = HomesEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(HomesIntent.Setup)
        }
        onEffect = homesEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    HomesCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    HomesExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    HomesExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    HomesFoldVerticalView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
            )
        }
    }
}
