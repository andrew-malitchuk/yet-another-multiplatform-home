package dev.yamh.io.presentation.feature.device.source.settings

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsIntent
import dev.yamh.io.presentation.feature.device.source.settings.view.SettingExpandedView
import dev.yamh.io.presentation.feature.device.source.settings.view.SettingsCompactView
import dev.yamh.io.presentation.feature.device.source.settings.view.SettingsFoldVerticalView
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureIntent
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun SettingsScreen(
    device: DeviceEntity
) {
    val viewModel: SettingsViewModel = getKoin().get<SettingsViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val settingsEffectHandler = SettingsEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(SettingsIntent.Setup(device))
        }
        onEffect = settingsEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    SettingsCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    SettingExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    SettingsFoldVerticalView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    SettingExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
