package dev.yamh.io.presentation.feature.main.main

import androidx.lifecycle.viewModelScope
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.toDeviceType
import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.home.GetSelectedHomeUseCase
import dev.yamh.domain.usecase.source.room.GetRoomsByHomeUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.main.main.mvi.MainMviEffect
import dev.yamh.io.presentation.feature.main.main.mvi.MainIntent
import dev.yamh.io.presentation.feature.main.main.mvi.MainNavigationMviEffect
import dev.yamh.io.presentation.feature.main.main.mvi.MainNavigationMviEffect.*
import dev.yamh.io.presentation.feature.main.main.mvi.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty1

internal class MainViewModel(
    private val getSelectedHomeUseCase: GetSelectedHomeUseCase,
    private val getRoomsByHomeUseCase: GetRoomsByHomeUseCase,
    private val getDeviceAttributeUseCase: GetDeviceAttributeUseCase

) :
    BaseViewModel<MainState, MainIntent, MainMviEffect>() {

    override val state: StateFlow<MainState> = MutableStateFlow(MainState())

    override val intent: StateFlow<MainIntent?> = MutableStateFlow(null)

    override val effect = Channel<MainMviEffect>(Channel.CONFLATED)

    override fun onIntent(intent: MainIntent) {
        when (intent) {

            MainIntent.Setup -> {
                executeCoroutine {
                    getSelectedHome()
                }
            }

            MainIntent.OnMainDoneIntent -> onEffect(GoToAuthorizationMviEffect)
            is MainIntent.GoToRoomIntent -> onEffect(
                GoToRoomMviEffect(
                    intent.roomEntity
                )
            )

            MainIntent.GoToSettingsIntent -> onEffect(GoToSettingsEffect)
            MainIntent.GoToHomeIntent -> onEffect(GoToHomeEffect)
            is MainIntent.GoToDeviceIntent -> intent.device.generalType()?.let {
                onEffect(
                    GoToDeviceEffect(
                        intent.device
                    )
                )
            }

            MainIntent.RefreshIntent -> {
                executeCoroutine {
                    getSelectedHome(true)
                }
            }
        }
    }

    override fun onEffect(effect: MainMviEffect) {
        executeCoroutine {
            this@MainViewModel.effect.send(effect)
        }
    }

    private fun getSelectedHome(isRefreshing: Boolean = false) {
        executeResult(
            request = {
//                delay(5000) // Simulate loading
                getSelectedHomeUseCase()
            },
            result = { result ->
                result?.let {
                    updateState {
                        copy(selectedHome = it)
                    }
                    getRooms(it)
                }
            },
            loading = { isLoading ->
                when (isRefreshing) {
                    true -> updateState {
                        copy(isRefreshing = isLoading)
                    }

                    false -> updateState {
                        copy(isLoading = isLoading)
                    }
                }
            }

        )
    }

    private fun getRooms(homeEntity: HomeEntity) {
        executeResult(
            request = {
                getRoomsByHomeUseCase(homeEntity)
            },
            result = { result ->
                result?.let { list ->
                    list.forEach {
                        it.selectedDevices = getDeviceAttribute(it.selectedDevices)
                    }
                    updateState {
                        copy(rooms = list)
                    }
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
                        }
                    }
                )
            }
        }

        return mutableDevices
    }
}


