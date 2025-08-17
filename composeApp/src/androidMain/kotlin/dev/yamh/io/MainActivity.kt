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

public class MainActivity : ComponentActivity() {

    public val nativeHomeClient: HomeClient by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        nativeHomeClient.registerActivityResultCallerForPermissions(this@MainActivity)
        
        setContent {
            App()
        }
    }


}

@Preview
@Composable
private fun AppAndroidPreview() {
    App()
}