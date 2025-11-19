package dev.yamh.io.presentation.core.platform.source.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import kotlin.coroutines.CoroutineContext

public abstract class BaseViewModel<State : MviState, Intent : MviIntent, Effect : MviEffect> :
    ViewModel(), KoinComponent {

    public abstract val state: StateFlow<State>

    public abstract val intent: StateFlow<Intent?>

    public abstract val effect: Channel<Effect>

    public abstract fun onIntent(intent: Intent)

    public abstract fun onEffect(effect: Effect)

    public open fun initialize(): Unit = Unit

    init {
        initialize()
    }

    public fun <T> executeCoroutine(
        context: CoroutineContext = Dispatchers.Default,
        scope: CoroutineScope = viewModelScope,
        debounce: Long? = null,
        loading: ((Boolean) -> Unit)? = null,
        result: ((T?) -> Unit)? = null,
        errorBlock: ((Throwable) -> Unit)? = null,
        request: suspend CoroutineScope.() -> T?,
    ): Job {
        return scope.launch {
            try {
                debounce?.let {
                    delay(it)
                }
                loading?.invoke(true)
                withContext(context) { request() }.apply {
                    this.let { result?.invoke(it) }
                }
            } catch (e: Throwable) {
                errorBlock?.invoke(e)
                loading?.invoke(false)
            } finally {
                loading?.invoke(false)
            }
        }
    }

    public fun <T> executeResult(
        context: CoroutineContext = Dispatchers.Default,
        scope: CoroutineScope = viewModelScope,
        debounce: Long? = null,
        loading: ((Boolean) -> Unit)? = null,
        result: ((T?) -> Unit)? = null,
        errorBlock: ((Throwable) -> Unit)? = null,
        request: suspend CoroutineScope.() -> Result<T>?,
    ): Job {
        return scope.launch {
            try {
                debounce?.let {
                    delay(it)
                }
                loading?.invoke(true)
                withContext(context) {
                    request()
                }.apply {
                    this.let { monad ->
                        monad?.fold(
                            onSuccess = { result?.invoke(it) },
                            onFailure = { errorBlock?.invoke(it) }
                        )
                    }
                }
            } catch (e: Throwable) {
                errorBlock?.invoke(e)
                loading?.invoke(false)
            } finally {
                loading?.invoke(false)
            }
        }
    }

    protected fun <T> StateFlow<T>.setValue(value: T) {
        (this as? MutableStateFlow)?.update {
            value
        }
    }

    protected fun updateState(
        block: State.() -> State
    ): Job = executeCoroutine {
        state.setValue(state.value.block())
    }
}