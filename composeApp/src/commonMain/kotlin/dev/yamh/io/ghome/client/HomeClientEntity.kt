package dev.yamh.io.ghome.client

import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.miscellaneous.PermissionsResultEntity

public expect class HomeClientEntity {

    public constructor()

    public suspend fun getDevices(): List<DeviceEntity>


    public suspend fun getDevice(id: String): DeviceEntity?


    public suspend fun getDeviceTypes(id: String): List<String>


    public suspend fun requestPermissions(forceLaunch: Boolean = false): PermissionsResultEntity

}