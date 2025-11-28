package dev.yamh.io.presentation.feature.settings.settings

import dev.yamh.domain.usecase.source.SetLanguageUseCase
import dev.yamh.domain.usecase.source.SetThemeUseCase
import dev.yamh.domain.usecase.source.SubscribeToLanguageChangesUseCase
import dev.yamh.domain.usecase.source.SubscribeToThemeChangesUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsIntent
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsMviEffect
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsNavigationMviEffect
import dev.yamh.io.presentation.feature.settings.settings.mvi.SettingsState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class SettingsViewModel(
    private val setLanguageUseCase: SetLanguageUseCase,
    private val setThemeUseCase: SetThemeUseCase,
    private val getLanguageUseCase: SubscribeToLanguageChangesUseCase,
    private val getThemeUseCase: SubscribeToThemeChangesUseCase,
) : BaseViewModel<SettingsState, SettingsIntent, SettingsMviEffect>() {

    override val state: StateFlow<SettingsState> = MutableStateFlow(SettingsState())

    override val intent: StateFlow<SettingsIntent?> = MutableStateFlow(null)

    override val effect: Channel<SettingsMviEffect> = Channel(Channel.CONFLATED)

    override fun initialize() {
        executeCoroutine {
            getLanguageUseCase().collect { result ->
                result.onSuccess { language ->
                    updateState {
                        copy(language = language)
                    }
                    executeCoroutine {
                        getThemeUseCase().collect { result ->
                            result.onSuccess { theme ->
                                updateState {
                                    copy(theme = theme)
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onIntent(intent: SettingsIntent) {
        when (intent) {

            SettingsIntent.OnBackClickIntent -> onEffect(SettingsNavigationMviEffect.OnNavBackClickMviEffect)
            SettingsIntent.Setup -> Unit

            is SettingsIntent.OnLanguageChangeIntent -> {
                updateState {
                    copy(language = intent.lang)
                }
                executeCoroutine {
                    setLanguageUseCase(intent.lang)
                }
            }

            is SettingsIntent.OnThemeChangeIntent -> {
                updateState {
                    copy(theme = intent.theme)
                }
                executeCoroutine {
                    setThemeUseCase(intent.theme)
                }
            }

            SettingsIntent.OnGoToGithubIntent -> onEffect(SettingsNavigationMviEffect.OnGoToGithubMviEffect)
            SettingsIntent.OnGoToLinkedInIntent -> onEffect(SettingsNavigationMviEffect.OnGoToLinkedInMviEffect)
        }
    }

    override fun onEffect(effect: SettingsMviEffect) {
        executeCoroutine {
            this@SettingsViewModel.effect.send(effect)
        }

    }
}


