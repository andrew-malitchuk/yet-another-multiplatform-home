package dev.yamh.io.presentation.core.platform.source.mvi

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@DslMarker
public annotation class MviDsl

@MviDsl
public class MviOrchestratorScope<State, Intent, Effect> {

    public var onIntent: ((Intent) -> Unit)? = null
    public var onEffect: ((Effect) -> Unit)? = null
    public var render: (@Composable (State) -> Unit)? = null

    public var setup: (suspend () -> Unit)? = null

    public fun onIntent(block: (Intent) -> Unit) {
        onIntent = block
    }

    public fun onEffect(block: (Effect) -> Unit) {
        onEffect = block
    }

    public fun render(block: @Composable (State) -> Unit) {
        render = block
    }

    public fun setup(block: suspend () -> Unit) {
        setup = block
    }
}

@Composable
public fun <State, Intent, Effect> MviOrchestrator(
    state: State,
    intent: Intent?,
    effect: Flow<Effect>?,
    block: MviOrchestratorScope<State, Intent, Effect>.() -> Unit
) {
    val scope = remember { MviOrchestratorScope<State, Intent, Effect>().apply(block) }

    // Run setup once, when it's defined
    LaunchedEffect(scope.setup) {
        scope.setup?.invoke()
    }
    // React to non-null intents
    LaunchedEffect(intent) {
        intent?.let { scope.onIntent?.invoke(it) }
    }

    val onEffectU by rememberUpdatedState(scope.onEffect)

    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner, effect) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            // collect both concurrently so one doesn’t block the other
            launch {
                effect?.collect { e -> onEffectU?.invoke(e) }
            }
        }
    }


    // Render always
    scope.render?.invoke(state)
}
