package dev.yamh.io.presentation.feature.device.source.switcher

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherIntent
import dev.yamh.io.presentation.feature.device.source.switcher.view.SwitcherCompactView
import dev.yamh.io.presentation.feature.device.source.switcher.view.SwitcherExpandedView
import dev.yamh.io.presentation.feature.device.source.switcher.view.SwitcherFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun SwitcherScreen(
    device: DeviceEntity
) {
    val viewModel: SwitcherViewModel = getKoin().get<SwitcherViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val switcherEffectHandler = SwitcherEffectHandler(this@NavHostController)

    LaunchedEffect(Unit) {
        viewModel.onIntent(SwitcherIntent.Setup(device))
    }

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(SwitcherIntent.Setup(device))
        }
        onEffect = switcherEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    SwitcherCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    SwitcherExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    SwitcherExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    SwitcherFoldVerticalView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
