package dev.yamh.io

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts.StartIntentSenderForResult
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.home.matter.commissioning.CommissioningResult
import com.google.home.HomeClient
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.core.component.inject

class MainActivity : ComponentActivity() {

    val nativeHomeClient: HomeClient by inject()

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        nativeHomeClient.registerActivityResultCallerForPermissions(this@MainActivity)

        setContent {
            App()
        }
    }

    private suspend fun commissioningCallback(activityResult: ActivityResult) {
        try {
            // Try to convert ActivityResult into CommissioningResult:
            val result: CommissioningResult = CommissioningResult.fromIntentSenderResult(
                activityResult.resultCode, activityResult.data
            )
            // Store the CommissioningResult in the StateFlow:
        } catch (exception: ApiException) {
            // Record the exception for commissioning failure:
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}