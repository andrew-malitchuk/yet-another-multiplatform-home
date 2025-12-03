package dev.yamh.io.presentation.feature.device.source.temperature

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureIntent
import dev.yamh.io.presentation.feature.device.source.temperature.view.TemperatureCompactView
import dev.yamh.io.presentation.feature.device.source.temperature.view.TemperatureExpandedView
import dev.yamh.io.presentation.feature.device.source.temperature.view.TemperatureFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun TemperatureScreen(
    device: DeviceEntity
) {
    val viewModel: TemperatureViewModel = getKoin().get<TemperatureViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val temperatureEffectHandler = TemperatureEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(TemperatureIntent.Setup(device))
        }
        onEffect = temperatureEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    TemperatureCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    TemperatureExpandedView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    TemperatureFoldVerticalView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    TemperatureExpandedView(
                        state=state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
