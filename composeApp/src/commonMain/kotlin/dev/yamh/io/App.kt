package dev.yamh.io

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.yamh.io.data.ghome.ghome.foobar.HomeClientModel
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.OnOffDeviceAttribute.Companion.turnOff
import dev.yamh.io.presentation.core.navigation.GlobalNavigation
import dev.yamh.io.presentation.feature.homes.navigation.homesNavigationGraph
import dev.yamh.io.presentation.feature.rooms.navigation.roomsNavigationGraph
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
public fun App() {

    val homeClientModel: HomeClientModel = HomeClientModel()


    val scope = rememberCoroutineScope()

    MaterialTheme {

        scope.launch {
            val foo = homeClientModel.getHomes()
            val selectedHome = foo.firstOrNull()

            val rooms = selectedHome?.getRooms()
            rooms.toString()

            val devices = rooms?.firstOrNull()?.getDevices()
            devices.toString()


            val controls = device?.controls

            controls.toString()


            controls?.forEach { control ->
                control.attributes.forEach { attribute ->

                }
            }

            with(homeClientModel) {
                device?.turnOff()
            }

//            selectedHome?.getRoom("")


        }

        HostNavigationGraph(
            navHostController = rememberNavController(),
            startDestination = GlobalNavigation.Homes,
        )
    }
}


@Composable
public fun HostNavigationGraph(
    navHostController: NavHostController,
    startDestination: Any,
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
    ) {
        with(navHostController) {
            homesNavigationGraph()
            roomsNavigationGraph()
        }
    }
}