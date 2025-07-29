package dev.yamh.io.ghome.client

import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.miscellaneous.PermissionsResultEntity

expect class HomeClientEntity {

    constructor()

    suspend fun getDevices(): List<DeviceEntity>


    suspend fun getDevice(id: String): DeviceEntity?


    suspend fun getDeviceTypes(id: String): List<String>


    suspend fun requestPermissions(forceLaunch: Boolean = false): PermissionsResultEntity

}