package dev.yamh.io.presentation.feature.main.main.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviOrchestrator
import dev.yamh.io.presentation.core.ui.source.kit.atom.card.EmptyCard
import dev.yamh.io.presentation.core.ui.source.kit.atom.container.FoldHorizontalLayout
import dev.yamh.io.presentation.feature.main.main.mvi.MainIntent
import dev.yamh.io.presentation.feature.main.main.mvi.MainState
import dev.yamh.io.presentation.feature.room.room.RoomViewModel
import dev.yamh.io.presentation.feature.room.room.mvi.RoomIntent
import dev.yamh.io.presentation.feature.room.room.mvi.RoomNavigationMviEffect
import dev.yamh.io.presentation.feature.room.room.view.RoomCompactSimplifiedView
import dev.yamh.presentation.core.styling.core.Theme
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.compose.getKoin

@Composable
public fun MainExpandedView(
    state: MainState,
    onIntent: (MainIntent) -> Unit,
) {
    var roomEntity: RoomEntity? by remember { mutableStateOf(null) }

    @Composable
    fun RoomScreenSimplified(
        roomEntity: RoomEntity
    ) {

        val viewModel: RoomViewModel = getKoin().get<RoomViewModel>()

        val state = viewModel.state.collectAsStateWithLifecycle()
        val intent = viewModel.intent.collectAsStateWithLifecycle(initialValue = null)
        val effect = viewModel.effect.receiveAsFlow()


        LaunchedEffect(Unit, roomEntity) {
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
            onEffect = { effect ->
                when (effect) {
                    is RoomNavigationMviEffect.GoToDeviceEffect -> onIntent(
                        MainIntent.GoToDeviceIntent(
                            effect.device
                        )
                    )

                    else -> Unit
                }

            }
            render { state ->
                RoomCompactSimplifiedView(
                    state = state,
                    onIntent = viewModel::onIntent
                )

            }
        }
    }


    FoldHorizontalLayout(
        start = {
            MainCompactView(
                state = state,
                onIntent = { intent ->

                    when (intent) {
                        is MainIntent.GoToRoomIntent -> {
                            roomEntity = intent.roomEntity
                        }

                        else -> onIntent(intent)
                    }
                }
            )
        },
        end = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Theme.color.primary0)
            ) {
                when (roomEntity != null) {
                    true -> RoomScreenSimplified(
                        roomEntity!!
                    )

                    false -> EmptyCard(
                        modifier = Modifier
                            .safeContentPadding()
                            .padding(16.dp),
                        background = Theme.color.primary0,
                    )
                }
            }

        }
    )

}