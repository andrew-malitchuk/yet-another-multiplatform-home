package dev.yamh.io.presentation.feature.splash.splash

import androidx.lifecycle.viewModelScope
import dev.yamh.domain.usecase.source.authorization.CheckAuthorizationUseCase
import dev.yamh.domain.usecase.source.home.GetSelectedHomeUseCase
import dev.yamh.domain.usecase.source.onboarding.IsOnboardingCompletedUseCase
import dev.yamh.domain.usecase.source.onboarding.SetOnboardingCompletedUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashIntent
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashMviEffect
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashNavigationMviEffect
import dev.yamh.io.presentation.feature.splash.splash.mvi.SplashState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class SplashViewModel(
    private val isOnboardingCompletedUseCase: IsOnboardingCompletedUseCase,
    private val setOnboardingCompletedUseCase: SetOnboardingCompletedUseCase,
    private val checkAuthorizationUseCase: CheckAuthorizationUseCase,
    private val getSelectedHomeUseCase: GetSelectedHomeUseCase,
) :
    BaseViewModel<SplashState, SplashIntent, SplashMviEffect>() {

    override val state: StateFlow<SplashState> = MutableStateFlow(SplashState())

    override val intent: StateFlow<SplashIntent?> = MutableStateFlow(null)

    override val effect: Channel<SplashMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: SplashIntent) {
        when (intent) {
            is SplashIntent.Setup -> checkOnboardingStatus()
            SplashIntent.GoToMainIntent -> onEffect(SplashNavigationMviEffect.GoToMainMviEffect)
            SplashIntent.GoToAuthIntent -> onEffect(SplashNavigationMviEffect.GoToAuthMviEffect)
            SplashIntent.GoToOnboardingIntent -> onEffect(SplashNavigationMviEffect.GoToOnboardingMviEffect)
        }
    }

    override fun onEffect(effect: SplashMviEffect) {
        executeCoroutine {
            this@SplashViewModel.effect.send(effect)
        }
    }

    private fun checkOnboardingStatus() {
        executeResult(
            request = {
                delay(DELAY) // Simulate splash screen delay
                isOnboardingCompletedUseCase()
            },
            result = {
                updateState {
                    copy(isLoading = false, isOnboardingCompleted = it)
                }
                when (it) {
                    true -> checkAuthorization()
                    else -> onEffect(SplashNavigationMviEffect.GoToOnboardingMviEffect)
                }
                setOnboardingCompleted()
            }
        )
    }

    private fun setOnboardingCompleted() {
        executeCoroutine {
            setOnboardingCompletedUseCase(true)
        }
    }

    private fun checkAuthorization() {
        executeResult(
            request = {
                checkAuthorizationUseCase()
            },
            result = {
                when (it) {
                    true -> checkSelectedHome()
                    else -> onEffect(SplashNavigationMviEffect.GoToAuthMviEffect)
                }
            }
        )
    }

    private fun checkSelectedHome() {
        executeResult(
            request = {
                getSelectedHomeUseCase()
            },
            result = {
                when (it != null) {
                    true -> onEffect(SplashNavigationMviEffect.GoToMainMviEffect)
                    else -> onEffect(SplashNavigationMviEffect.GoToAuthMviEffect)
                }
            }
        )
    }

    companion object {
        const val DELAY = 1_500L
    }

}
