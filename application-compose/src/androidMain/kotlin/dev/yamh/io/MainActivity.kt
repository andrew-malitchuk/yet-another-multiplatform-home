package dev.yamh.io

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.home.HomeClient
import org.koin.android.ext.android.inject

public class MainActivity : ComponentActivity() {

    public val nativeHomeClient: HomeClient by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val device = intent.getStringExtra("device")

        nativeHomeClient.registerActivityResultCallerForPermissions(this@MainActivity)
        
        setContent {
            App(
                device = device
            )
        }
    }


}

@Preview
@Composable
private fun AppAndroidPreview() {
    App()
}