package dev.yamh.io.presentation.feature.homes.homes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.yamh.io.data.ghome.ghome.foobar.HomeClientModel
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel
import dev.yamh.io.presentation.core.ui.core.ext.noRippleClickable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
public fun HomesScreen() {

    val homeClientModel = HomeClientModel()

    val homesState = homeClientModel.getHomesFlow().collectAsStateWithLifecycle(null)
    val homeState =
        homeClientModel.getHomeFlow(Id("structure@356299f5-6f09-42ab-978f-b2971f479487"))
            .collectAsStateWithLifecycle(null)

    Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

        homesState.value?.forEach {
            Text(
                modifier = Modifier.noRippleClickable {

                },
                text = "Home: ${it.name} - ${it.id.value}"
            )
        }

    }

}