package dev.yamh.io.presentation.feature.device.source.switcher

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.ColorControlAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.DimmableAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.WindowCoveringEntity
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.usecase.source.device.ChangeDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.SubscribeToDeviceUpdatesUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherIntent
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherMviEffect
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherNavigationMviEffect
import dev.yamh.io.presentation.feature.device.source.switcher.mvi.SwitcherState
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class SwitcherViewModel(
    private val subscribeToDeviceUpdatesUseCase: SubscribeToDeviceUpdatesUseCase,
    private val changeDeviceAttributeUseCase: ChangeDeviceAttributeUseCase,
    private val getDeviceAttributeUseCase: GetDeviceAttributeUseCase
) : BaseViewModel<SwitcherState, SwitcherIntent, SwitcherMviEffect>() {

    override val state: StateFlow<SwitcherState> = MutableStateFlow(SwitcherState())

    override val intent: StateFlow<SwitcherIntent?> = MutableStateFlow(null)

    override val effect: Channel<SwitcherMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: SwitcherIntent) {
        when (intent) {

            SwitcherIntent.OnBackClickIntent -> onEffect(SwitcherNavigationMviEffect.OnNavBackClickMviEffect)
            is SwitcherIntent.Setup -> {
                updateState {
                    copy(
                        device = intent.device
                    )
                }
                subscribeToDeviceUpdates(intent.device)
                getDeviceAttribute()
            }

            SwitcherIntent.GoToMainIntent -> onEffect(SwitcherNavigationMviEffect.GoToMainMviEffect)
            is SwitcherIntent.GoToSettingsIntent -> onEffect(
                SwitcherNavigationMviEffect.GoToSettingsMviEffect(
                    device = intent.device
                )
            )

            is SwitcherIntent.ChangeAttribute -> {
                changeAttribute(intent.attribute)
            }
        }
    }

    override fun onEffect(effect: SwitcherMviEffect) {
        executeCoroutine {
            this@SwitcherViewModel.effect.send(effect)
        }
    }


    private fun subscribeToDeviceUpdates(device: DeviceEntity) {
        device.type.forEach { type ->
            type?.let {
                executeCoroutine {
                    subscribeToDeviceUpdatesUseCase(device, it).collect { result ->
                        println("result: $result")
                        result.onSuccess { attribute ->
                            println("device: $attribute")

                            when (attribute) {
                                is DimmableAttributeEntity -> updateState {
                                    copy(
                                        level = attribute.level.toFloat()
                                    )
                                }

                                is OnOffAttributeEntity -> updateState {
                                    copy(
                                        onOff = attribute.isOn
                                    )
                                }

                                is ColorControlAttributeEntity -> TODO()
                                is TemperatureAttributeEntity -> {
                                    println("temperature: ${attribute.temperature}")
                                }

                                is ContactAttributeEntity -> {
                                    println("temperature: ${attribute.isOpen}")
                                }

                                is WindowCoveringEntity -> {}
                            }
                        }
                    }
                }
            }

        }
    }

    private var job: Job? = null

    private fun changeAttribute(attribute: AttributeEntity) {
        val device = state.value.device ?: return
        val type = when (attribute) {
            is DimmableAttributeEntity -> DeviceType.Dimmable
            is OnOffAttributeEntity -> DeviceType.OnOff
            is ColorControlAttributeEntity -> DeviceType.ColorControl
            is TemperatureAttributeEntity -> DeviceType.TemperatureSensor
            is ContactAttributeEntity -> DeviceType.Contact
            is WindowCoveringEntity -> DeviceType.WindowCovering
        }
        job?.cancel()
        job = null
        job = executeCoroutine(
            debounce = 1_000L,
            loading = {
                updateState {
                    copy(isLoading = it)
                }
            }
        ) {
            changeDeviceAttributeUseCase(
                device,
                type,
                attribute
            )
        }
    }

    private fun getDeviceAttribute() {
        state.value.device?.let { device ->
            device.type.filterNotNull().forEach {
                executeResult(
                    request = {
                        getDeviceAttributeUseCase(device, it)
                    },
                    result = { attribute ->
                        when (attribute) {
                            is DimmableAttributeEntity -> updateState {
                                copy(
                                    level = attribute.level.toFloat()
                                )
                            }

                            is OnOffAttributeEntity -> updateState {
                                copy(
                                    onOff = attribute.isOn
                                )
                            }

                            is ColorControlAttributeEntity -> TODO()
                            is TemperatureAttributeEntity -> {
                                println("temperature: ${attribute.temperature}")
                            }

                            is ContactAttributeEntity -> {
                                println("temperature: ${attribute.isOpen}")
                            }

                            else -> Unit
                        }
                    }
                )
            }
        }
    }
}


