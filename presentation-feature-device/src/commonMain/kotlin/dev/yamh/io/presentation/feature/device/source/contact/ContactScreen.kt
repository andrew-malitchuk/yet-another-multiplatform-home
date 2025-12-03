package dev.yamh.io.presentation.feature.device.source.contact

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactIntent
import dev.yamh.io.presentation.feature.device.source.contact.view.ContactCompactView
import dev.yamh.io.presentation.feature.device.source.contact.view.ContactExpandedView
import dev.yamh.io.presentation.feature.device.source.contact.view.ContactFoldVerticalView
import dev.yamh.io.presentation.feature.device.source.temperature.view.TemperatureCompactView
import dev.yamh.io.presentation.feature.device.source.temperature.view.TemperatureExpandedView
import dev.yamh.io.presentation.feature.device.source.temperature.view.TemperatureFoldVerticalView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun ContactScreen(
    device: DeviceEntity
) {

    val viewModel: ContactViewModel = getKoin().get<ContactViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val contactEffectHandler = ContactEffectHandler(this@NavHostController)

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(ContactIntent.Setup(device))
        }
        onEffect = contactEffectHandler::proceedEffect
        render { state ->
            SizedAdaptiveContainer(
                compact = {
                    ContactCompactView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                expanded = {
                    ContactExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldVertical = {
                    ContactFoldVerticalView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                },
                foldHorizontal = {
                    ContactExpandedView(
                        state = state,
                        onIntent = viewModel::onIntent
                    )
                }
            )
        }
    }
}
