package dev.yamh.io.data.ghome.ghome.client

import dev.yamh.io.data.ghome.ghome.device.DeviceEntity
import dev.yamh.io.data.ghome.ghome.miscellaneous.PermissionsResultEntity

actual class HomeClientEntity {


    actual suspend fun getInstance(): HomeClientEntity {
        TODO("Not yet implemented")
    }

    actual suspend fun requestPermissions(forceLaunch: Boolean): PermissionsResultEntity {
        TODO("Not yet implemented")
    }

    actual suspend fun getDevices(): List<DeviceEntity> {
        TODO("Not yet implemented")
    }
}