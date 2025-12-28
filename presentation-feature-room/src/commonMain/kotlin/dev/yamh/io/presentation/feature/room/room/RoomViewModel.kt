package dev.yamh.io.presentation.feature.room.room

import androidx.lifecycle.viewModelScope
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.toDeviceType
import dev.yamh.domain.core.source.model.room.RoomEntity
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetDevicesByRoomUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.room.room.mvi.RoomIntent
import dev.yamh.io.presentation.feature.room.room.mvi.RoomMviEffect
import dev.yamh.io.presentation.feature.room.room.mvi.RoomNavigationMviEffect
import dev.yamh.io.presentation.feature.room.room.mvi.RoomNavigationMviEffect.*
import dev.yamh.io.presentation.feature.room.room.mvi.RoomState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

public class RoomViewModel(
    private val getDevicesByRoomUseCase: GetDevicesByRoomUseCase,
    private val getDeviceAttributeUseCase: GetDeviceAttributeUseCase
) :
    BaseViewModel<RoomState, RoomIntent, RoomMviEffect>() {

    override val state: StateFlow<RoomState> = MutableStateFlow(RoomState())

    override val intent: StateFlow<RoomIntent?> = MutableStateFlow(null)

    override val effect: Channel<RoomMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: RoomIntent) {
        when (intent) {

            RoomIntent.OnBackClickIntent -> onEffect(OnNavBackClickMviEffect)
            is RoomIntent.Setup -> {
                updateState {
                    copy(
                        room = intent.room
                    )
                }
                getDevices(intent.room)
            }

            RoomIntent.GoToMainIntent -> onEffect(GoToMainMviEffect)
            is RoomIntent.GoToDeviceIntent ->
                intent.device.generalType()?.let {
                    onEffect(
                        GoToDeviceEffect(
                            intent.device
                        )
                    )
                }

            is RoomIntent.SearchIntent -> search(intent.query)
            RoomIntent.RefreshIntent -> {
                getDevices()
            }
        }
    }

    override fun onEffect(effect: RoomMviEffect) {
        executeCoroutine {
            this@RoomViewModel.effect.send(effect)
        }
    }

    private fun getDevices() {
        state.value.room?.let { room ->
            executeResult(
                request = {
                    getDevicesByRoomUseCase(room)
                },
                result = { result ->
                    result?.let {
                        executeCoroutine {
                            getDeviceAttribute(it).let { devicesWithAttributes ->
                                updateState {
                                    copy(
                                        devices = devicesWithAttributes
                                    )
                                }
                            }
                        }
                    }
                },
                loading = { isLoading ->
                    updateState {
                        copy(isRefreshing = isLoading)
                    }
                }
            )
        }
    }

    private fun getDevices(room: RoomEntity) {
        executeResult(
            request = {
                getDevicesByRoomUseCase(room)
            },
            result = { result ->
                result?.let {
                    executeCoroutine {
                        getDeviceAttribute(it).let { devicesWithAttributes ->
                            updateState {
                                copy(
                                    devices = devicesWithAttributes
                                )
                            }
                        }
                    }
                }
            },
            loading = {
                updateState {
                    copy(isLoading = isLoading)
                }
            }
        )
    }


    private fun getDeviceAttribute(devices: List<DeviceEntity>): List<DeviceEntity> {
        val mutableDevices = devices.toMutableList()

        mutableDevices.forEachIndexed { index, device ->
            device.generalType()?.toDeviceType()?.let { type ->
                executeResult(
                    request = { getDeviceAttributeUseCase(device, type) },
                    result = { result ->
                        result?.let { attr ->
                            val updatedDevice = device.copy(attribute = attr)
                            mutableDevices[index] = updatedDevice

                            // emit new immutable copy of the list so Compose re-renders
                            updateState {
                                copy(devices = mutableDevices.toList())
                            }
                        }
                    }
                )
            }
        }

        return mutableDevices
    }


    public fun search(query: String?) {
        if (query.isNullOrBlank()) {
            updateState {
                copy(
                    query = null,
                    searchResult = null
                )
            }
            return
        }
        executeCoroutine(
            debounce = DEBOUNCE
        ) {
            val all = state.value.devices

            val result =
                all?.filter {
                    it.name.value.contains(query, ignoreCase = true)
                }

            updateState {
                copy(
                    query = query,
                    searchResult = result
                )
            }
        }


    }

    public companion object {
        private const val DEBOUNCE = 500L
    }
}


