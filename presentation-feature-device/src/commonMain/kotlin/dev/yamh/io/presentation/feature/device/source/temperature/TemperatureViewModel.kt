package dev.yamh.io.presentation.feature.device.source.temperature

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.domain.usecase.source.device.MakeDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.SubscribeToDeviceUpdatesUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureIntent
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureMviEffect
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureNavigationMviEffect.*
import dev.yamh.io.presentation.feature.device.source.temperature.mvi.TemperatureState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class TemperatureViewModel(
    private val subscribeToDeviceUpdatesUseCase: SubscribeToDeviceUpdatesUseCase,
    private val makeDeviceSelectedUseCase: MakeDeviceSelectedUseCase,
) :
    BaseViewModel<TemperatureState, TemperatureIntent, TemperatureMviEffect>() {

    override val state: StateFlow<TemperatureState> = MutableStateFlow(TemperatureState())

    override val intent: StateFlow<TemperatureIntent?> = MutableStateFlow(null)

    override val effect: Channel<TemperatureMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: TemperatureIntent) {
        when (intent) {

            TemperatureIntent.OnBackClickIntent -> onEffect(OnNavBackClickMviEffect)
            is TemperatureIntent.Setup -> {
                updateState {
                    copy(
                        device = intent.device,
                        isLoading = false
                    )
                }
                subscribeToDeviceUpdates(intent.device)
            }

            TemperatureIntent.GoToMainIntent -> onEffect(GoToMainMviEffect)
            is TemperatureIntent.GoToSettingsIntent -> onEffect(GoToSettingsMviEffect(device = intent.device))
            TemperatureIntent.RefreshIntent -> updateDeviceState()
        }
    }

    override fun onEffect(effect: TemperatureMviEffect) {
        executeCoroutine {
            this@TemperatureViewModel.effect.send(effect)
        }
    }

    private fun subscribeToDeviceUpdates(device: DeviceEntity) {
        device.type.forEach { type ->
            type?.let {
                executeCoroutine {
                    subscribeToDeviceUpdatesUseCase(device, it).collect { result ->
                        result.onSuccess { attribute ->

                            (attribute as? TemperatureAttributeEntity)?.let { attribute ->
                                updateState {
                                    copy(
                                        temperature = attribute.temperature
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private fun updateDeviceState() {
        updateState {
            copy(
                isRefreshing = true
            )
        }
        state.value.device?.let { device ->
            device.type.forEach { type ->

                type?.let {
                    executeCoroutine {
                        subscribeToDeviceUpdatesUseCase(device, it).collect { result ->
                            result.onSuccess { attribute ->

                                (attribute as? TemperatureAttributeEntity)?.let { attribute ->
                                    updateState {
                                        copy(
                                            temperature = attribute.temperature,
                                            isRefreshing = false
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

}


