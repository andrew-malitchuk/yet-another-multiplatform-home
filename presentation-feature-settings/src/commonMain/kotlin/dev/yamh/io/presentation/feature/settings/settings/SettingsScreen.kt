package dev.yamh.io.presentation.feature.settings.settings

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dev.yamh.io.presentation.core.platform.core.util.Platform
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsIntent
import dev.yamh.io.presentation.feature.settings.settings.view.SettingsCompactView
import dev.yamh.io.presentation.feature.settings.settings.view.SettingsExpandedView
import dev.yamh.io.presentation.feature.settings.settings.view.SettingsFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun SettingsScreen() {

    val viewModel: SettingsViewModel = getKoin().get<SettingsViewModel>()

    val platform: Platform = getKoin().get<Platform>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val settingsEffectHandler = SettingsEffectHandler(this@NavHostController, platform)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(SettingsIntent.Setup)
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
                    SettingsExpandedView(
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
                    SettingsExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
