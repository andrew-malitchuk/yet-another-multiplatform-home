package dev.yamh.io.presentation.core.platform.source.device

import android.app.Activity
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import kotlinx.coroutines.flow.collectLatest

public actual object DeviceEnvironment {
    @Composable
    public actual fun getOrientation(): DeviceOrientation {
        val configuration = LocalConfiguration.current
        return when (configuration.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> DeviceOrientation.LANDSCAPE
            else -> DeviceOrientation.PORTRAIT
        }
    }

    @Composable
    public actual fun getPosture(): DevicePosture {
        val context = LocalContext.current
        val lifecycleOwner = LocalLifecycleOwner.current
        var posture by remember { mutableStateOf(DevicePosture.NORMAL) }

        LaunchedEffect(Unit) {
            val tracker = WindowInfoTracker.getOrCreate(context)
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                tracker.windowLayoutInfo(context as Activity)
                    .collectLatest { layoutInfo ->
                        val feature = layoutInfo.displayFeatures
                            .filterIsInstance<FoldingFeature>()
                            .firstOrNull()
                        posture = when (feature?.state) {
                            FoldingFeature.State.HALF_OPENED -> DevicePosture.HALF_OPENED
                            FoldingFeature.State.FLAT -> DevicePosture.FLAT
                            else -> DevicePosture.NORMAL
                        }
                    }
            }
        }
        return posture
    }
}