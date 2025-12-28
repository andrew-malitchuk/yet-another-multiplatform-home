package dev.yamh.io.presentation.feature.room.room

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.SizedAdaptiveContainer
import dev.yamh.io.presentation.feature.room.room.mvi.RoomIntent
import dev.yamh.io.presentation.feature.room.room.view.RoomCompactView
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
context(NavHostController)
public fun RoomScreen(
    roomEntity: RoomEntity
) {

    val viewModel: RoomViewModel = getKoin().get<RoomViewModel>()

    val state = viewModel.state.collectAsStateWithLifecycle()
    val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
    val effect = viewModel.effect.receiveAsFlow()

    val roomEffectHandler = RoomEffectHandler(this@NavHostController)

    LaunchedEffect(Unit) {
        viewModel.onIntent(RoomIntent.Setup(roomEntity))
    }

    MviOrchestrator(
        state = state.value,
        intent = intent.value,
        effect = effect,
    ) {
        setup {
            viewModel.onIntent(RoomIntent.Setup(roomEntity))
        }
        onEffect = roomEffectHandler::proceedEffect
        render { state ->
            RoomCompactView(
                state = state,
                onIntent = viewModel::onIntent
            )
        }
    }
}
