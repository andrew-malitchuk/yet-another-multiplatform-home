package dev.yamh.io.presentation.feature.device.source.window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.device.source.window.mvi.WindowIntent
import dev.yamh.io.presentation.feature.device.source.window.view.WindowCompactView
import dev.yamh.io.presentation.feature.device.source.window.view.WindowExpandedView
import dev.yamh.io.presentation.feature.device.source.window.view.WindowFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun WindowScreen(
    device: DeviceEntity
) {
    val viewModel: WindowViewModel = getKoin().get<WindowViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val windowEffectHandler = WindowEffectHandler(this@NavHostController)

    LaunchedEffect(Unit) {
        viewModel.onIntent(WindowIntent.Setup(device))
    }

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(WindowIntent.Setup(device))
        }
        onEffect = windowEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    WindowCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    WindowExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    WindowExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    WindowFoldVerticalView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
