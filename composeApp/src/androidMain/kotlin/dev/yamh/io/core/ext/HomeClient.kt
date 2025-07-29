package dev.yamh.io.core.ext

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.DeviceType
import com.google.home.HomeClient
import com.google.home.HomeDevice
import com.google.home.Trait
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlin.reflect.KClass


@RequiresApi(Build.VERSION_CODES.O_MR1)
suspend fun HomeClient.getDevices(): List<HomeDevice> {
    return devices().list().toList()
}


@RequiresApi(Build.VERSION_CODES.O_MR1)
suspend fun HomeClient.getDevice(id: String): HomeDevice? {
    return devices().list().firstOrNull { it.id.id == id }
}

@RequiresApi(Build.VERSION_CODES.O_MR1)
suspend fun HomeClient.getDeviceTypes(id: String): List<DeviceType>? {
    return getDevice(id)?.types()?.firstOrNull()?.toList()
}

@RequiresApi(Build.VERSION_CODES.O_MR1)
suspend fun HomeClient.getDeviceTrait(id: String, origin: KClass<*>): Trait? {
    return getDeviceTypes(id)?.firstOrNull()?.traits()?.firstOrNull {
        origin.isInstance(it)
    }
}

