package dev.yamh.io.presentation.feature.main.main

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.main.main.mvi.MainIntent
import dev.yamh.io.presentation.feature.main.main.view.MainCompactView
import dev.yamh.io.presentation.feature.main.main.view.MainExpandedView
import dev.yamh.io.presentation.feature.main.main.view.MainFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun MainScreen() {

    val viewModel: MainViewModel = getKoin().get<MainViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val mainEffectHandler = MainEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(MainIntent.Setup)
        }
        onEffect = mainEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    MainCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    MainExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    MainFoldVerticalView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    MainExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
