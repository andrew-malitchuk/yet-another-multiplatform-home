package dev.yamh.io.presentation.feature.authorization.authorization

import androidx.lifecycle.viewModelScope
import dev.yamh.domain.usecase.source.authorization.AuthorizeUseCase
import dev.yamh.domain.usecase.source.authorization.CheckAuthorizationUseCase
import dev.yamh.io.presentation.core.platform.source.mvi.BaseViewModel
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationMviEffect
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationIntent
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationNavigationMviEffect
import dev.yamh.io.presentation.feature.authorization.authorization.mvi.AuthorizationState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class AuthorizationViewModel(
    private val authorizeUseCase: AuthorizeUseCase,
    private val checkAuthorizationUseCase: CheckAuthorizationUseCase,
) :
    BaseViewModel<AuthorizationState, AuthorizationIntent, AuthorizationMviEffect>() {

    override val state: StateFlow<AuthorizationState> = MutableStateFlow(AuthorizationState())

    override val intent: StateFlow<AuthorizationIntent?> = MutableStateFlow(null)

    override val effect: Channel<AuthorizationMviEffect> = Channel(Channel.CONFLATED)

    override fun onIntent(intent: AuthorizationIntent) {
        when (intent) {

            AuthorizationIntent.OnAuthorizationDoneIntent -> onEffect(
                AuthorizationNavigationMviEffect.GoToHomesMviEffect
            )

            AuthorizationIntent.Setup -> Unit

            AuthorizationIntent.OnAuthorizeIntent -> authorize()
        }
    }

    override fun onEffect(effect: AuthorizationMviEffect) {
        executeCoroutine {
            this@AuthorizationViewModel.effect.send(effect)
        }
    }

    private fun authorize() {
        executeResult(
            request = {
                authorizeUseCase()
            },
            result = { result ->
                when (result) {
                    true -> onEffect(
                        AuthorizationNavigationMviEffect.GoToHomesMviEffect
                    )

                    else -> Unit
                }
            },
            loading = {
                updateState {
                    copy(
                        isLoading = it
                    )
                }
            },
            errorBlock = {
                updateState {
                    copy(
                        error = it.message
                    )
                }
            }
        )
    }

}


