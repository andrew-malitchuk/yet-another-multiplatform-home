package dev.yamh.io.presentation.feature.device.source.settings

import androidx.lifecycle.viewModelScope
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.domain.usecase.source.device.IsDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.MakeDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.SetCustomDeviceTypeUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsIntent
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsMviEffect
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.settings.mvi.SettingsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class SettingsViewModel(
    val makeDeviceSelectedUseCase: MakeDeviceSelectedUseCase,
    val isDeviceSelectedUseCase: IsDeviceSelectedUseCase,
    val setCustomDeviceTypeUseCase: SetCustomDeviceTypeUseCase
) : BaseViewModel<SettingsState, SettingsIntent, SettingsMviEffect>() {

    override val state: StateFlow<SettingsState> = MutableStateFlow(SettingsState())

    override val intent: StateFlow<SettingsIntent?> = MutableStateFlow(null)

    override val effect: Channel<SettingsMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: SettingsIntent) {
        when (intent) {

            SettingsIntent.OnBackClickIntent -> onEffect(SettingsNavigationMviEffect.OnNavBackClickMviEffect)
            is SettingsIntent.Setup -> {
                updateState {
                    copy(
                        device = intent.device
                    )
                }
                getDeviceSelectedStatus(device = intent.device)
            }

            SettingsIntent.OnSettingsDoneIntent -> onEffect(SettingsNavigationMviEffect.GoToAuthorizationMviEffect)
            is SettingsIntent.OnSettingsChangedIntent -> changeDeviceSelectedStatus(intent.newState)
            is SettingsIntent.OnDeviceCustomTypeChangedIntent -> changeCustomDeviceType(intent.newType)
        }
    }

    override fun onEffect(effect: SettingsMviEffect) {
        executeCoroutine {
            this@SettingsViewModel.effect.send(effect)
        }
    }

    private fun getDeviceSelectedStatus(device: DeviceEntity) {
        executeResult(
            request = {
                isDeviceSelectedUseCase(device)
            },
            result = { isDeviceSelected ->
                updateState {
                    copy(
                        isDeviceSelected = isDeviceSelected ?: false
                    )
                }
            }
        )
    }

    private fun changeDeviceSelectedStatus(isSelected: Boolean) {
        state.value.device?.let {
            executeResult(
                request = {
                    makeDeviceSelectedUseCase(it, isSelected)
                },
                result = { isDeviceSelected ->
                    updateState {
                        copy(
                            isDeviceSelected = isSelected
                        )
                    }

                }
            )
        }
    }


    private fun changeCustomDeviceType(newType: DeviceCustomType) {
        state.value.device?.let {
            executeResult(
                request = {
                    setCustomDeviceTypeUseCase(it, newType)
                },
                result = {
                    updateState {
                        copy(
                            device = device?.copy(customType = newType)
                        )
                    }
                }
            )
        }
    }
}


