package dev.yamh.io.presentation.feature.device.source.contact

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.usecase.source.device.GetDeviceByIdUseCase
import dev.yamh.domain.usecase.source.device.SubscribeToDeviceUpdatesUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactIntent
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactMviEffect
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.contact.mvi.ContactState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class ContactViewModel(
    private val subscribeToDeviceUpdatesUseCase: SubscribeToDeviceUpdatesUseCase,
    private val getDeviceByIdUseCase: GetDeviceByIdUseCase,

    ) :
    BaseViewModel<ContactState, ContactIntent, ContactMviEffect>() {

    override val state: StateFlow<ContactState> = MutableStateFlow(ContactState())

    override val intent: StateFlow<ContactIntent?> = MutableStateFlow(null)

    override val effect: Channel<ContactMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: ContactIntent) {
        when (intent) {

            ContactIntent.OnBackClickIntent -> onEffect(ContactNavigationMviEffect.OnNavBackClickMviEffect)
            is ContactIntent.Setup -> {
                updateState {
                    copy(
                        device = intent.device
                    )
                }
                subscribeToDeviceUpdates(intent.device)
                updateDevice()
            }

            ContactIntent.GoToMainIntent -> onEffect(ContactNavigationMviEffect.GoToMainMviEffect)
            is ContactIntent.GoToSettingsIntent -> onEffect(
                ContactNavigationMviEffect.GoToSettingsMviEffect(
                    device = intent.device
                )
            )
        }
    }

    override fun onEffect(effect: ContactMviEffect) {
        executeCoroutine {
            this@ContactViewModel.effect.send(effect)
        }
    }

    private fun subscribeToDeviceUpdates(device: DeviceEntity) {
        device.type.forEach { type ->
            type?.let {
                executeCoroutine {
                    subscribeToDeviceUpdatesUseCase(device, it).collect { result ->
                        result.onSuccess { attribute ->
                            (attribute as? ContactAttributeEntity)?.let { attribute ->
                                updateState {
                                    copy(
                                        isOpen = attribute.isOpen
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    fun updateDevice() {
        state.value.device?.id?.let {
            executeResult(
                request = {
                    getDeviceByIdUseCase(it)
                },
                result = { device ->
                    device?.let {
                        updateState {
                            copy(
                                device = device,
                                customType = device.customType
                            )
                        }
                    }
                }
            )
        }
    }

}