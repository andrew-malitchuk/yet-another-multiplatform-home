package dev.yamh.io.presentation.feature.homes.homes

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.usecase.source.home.GetHomesUseCase
import dev.yamh.domain.usecase.source.home.GetSelectedHomeUseCase
import dev.yamh.domain.usecase.source.home.SetSelectedHomeUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesIntent
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesMviEffect
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesNavigationMviEffect
import dev.yamh.io.presentation.feature.homes.homes.mvi.HomesState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class HomesViewModel(
    private val getHomesUseCase: GetHomesUseCase,
    private val getSelectedHomeUseCase: GetSelectedHomeUseCase,
    private val setSelectedHomeUseCase: SetSelectedHomeUseCase,
) :
    BaseViewModel<HomesState, HomesIntent, HomesMviEffect>() {

    override val state: StateFlow<HomesState> = MutableStateFlow(HomesState())

    override val intent: StateFlow<HomesIntent?> = MutableStateFlow(null)

    override val effect: Channel<HomesMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: HomesIntent) {
        when (intent) {
            HomesIntent.Setup -> {
                executeCoroutine {
                    getHomes()
                    getSelectedHome()
                }
            }

            HomesIntent.GoToMainIntent -> onEffect(HomesNavigationMviEffect.GoToMainMviEffect)
            HomesIntent.GoToSettingsIntent -> onEffect(HomesNavigationMviEffect.GoToSettingsEffect)
            is HomesIntent.OnSelectHomeIntent -> setSelectedHome(intent.home)
            HomesIntent.RefreshIntent -> {
                executeCoroutine {
                    getHomes(true)
                }
            }
        }
    }

    override fun onEffect(effect: HomesMviEffect) {
        executeCoroutine {
            this@HomesViewModel.effect.send(effect)
        }
    }


    private fun getHomes(isRefreshing: Boolean = false) {
        executeResult(
            request = {
                getHomesUseCase()
            },
            result = { result ->
                result?.let {
                    updateState {
                        copy(content = it)
                    }
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

    private fun getSelectedHome() {
        executeResult(
            request = {
                getSelectedHomeUseCase()
            },
            result = { result ->
                result?.let {
                    updateState {
                        copy(selectedHome = it)
                    }
                }
            },
            loading = {
                updateState {
                    copy(isLoading = it)
                }
            }
        )
    }

    private fun setSelectedHome(home: HomeEntity) {
        executeResult(
            request = {
                setSelectedHomeUseCase(home)
            },
            result = {
                onEffect(HomesNavigationMviEffect.GoToMainMviEffect)
            },
        )
    }

}


