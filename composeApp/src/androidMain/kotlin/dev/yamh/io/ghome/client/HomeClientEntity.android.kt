package dev.yamh.io.ghome.client

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.DeviceTypeFactory
import com.google.home.HomeClient
import com.google.home.Trait
import com.google.home.TraitFactory
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import dev.yamh.io.core.ext.getDevice
import dev.yamh.io.core.ext.getDeviceTypes
import dev.yamh.io.core.ext.getDevices
import dev.yamh.io.ghome.core.Configure
import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.device.attribute.LevelDeviceAttribute
import dev.yamh.io.ghome.device.attribute.OnOffDeviceAttribute
import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute
import dev.yamh.io.ghome.device.control.OnOffLightDeviceControl
import dev.yamh.io.ghome.device.control.base.DeviceControl
import dev.yamh.io.ghome.miscellaneous.PermissionsResultEntity
import dev.yamh.io.ghome.miscellaneous.PermissionsResultStatusEntity
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

actual class HomeClientEntity : KoinComponent {

    val nativeHomeClient: HomeClient by inject()


    // Boolean state (//device@b7cd50f9-4207-45a1-a715-d6535425f889)
    // Basic information (//device@24fda253-6a15-454c-997e-34dd5b0e6c76)
    // level control (//device@78d91f5d-7f1b-4c47-856f-df3d02dc270f)


    @RequiresApi(Build.VERSION_CODES.O_MR1)
    actual suspend fun getDevices(): List<DeviceEntity> {

        return nativeHomeClient.getDevices().map {
            DeviceEntity(
                id = it.id.id,
                name = it.name,
                isMatterDevice = it.isMatterDevice,
                isInStructure = it.isInStructure,
                isInRoom = it.isInRoom,
                roomId = it.roomId?.id,
                structureId = it.structureId?.id,
                agentId = it.agentId?.id,
                controls = getDeviceType(
                    deviceType = it.types().firstOrNull()?.toList(),
                    supportedType = Configure.supportedTypes
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    actual suspend fun requestPermissions(forceLaunch: Boolean): PermissionsResultEntity {
        val result = nativeHomeClient.requestPermissions(forceLaunch).let { original ->
            PermissionsResultEntity(
                status = PermissionsResultStatusEntity.entries
                    .firstOrNull { it.name == original.status.name }
                    ?: PermissionsResultStatusEntity.ERROR,
                errorMessage = original.errorMessage,
            )
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    actual suspend fun getDevice(id: String): DeviceEntity? {
        return nativeHomeClient.getDevice(id)?.let {
            DeviceEntity(
                id = it.id.id,
                name = it.name,
                isMatterDevice = it.isMatterDevice,
                isInStructure = it.isInStructure,
                isInRoom = it.isInRoom,
                roomId = it.roomId?.id,
                structureId = it.structureId?.id,
                agentId = it.agentId?.id,
                controls = getDeviceType(
                    deviceType = it.types().firstOrNull()?.toList(),
                    supportedType = Configure.supportedTypes
                )
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    actual suspend fun getDeviceTypes(id: String): List<String> {
        return nativeHomeClient.getDeviceTypes(id)?.map { it.toString() } ?: emptyList()
    }
}


fun getDeviceType(
    deviceType: List<com.google.home.DeviceType>?,
    supportedType: List<DeviceTypeFactory<out com.google.home.DeviceType>>
): List<DeviceControl> {
    val result = mutableListOf<DeviceControl>()
    deviceType?.forEach { originalDeviceType ->
        supportedType.forEach { supportedDeviceType ->
            if (originalDeviceType.factory.factory::class == supportedDeviceType.factory::class) {
                when (originalDeviceType) {
                    is OnOffLightDevice -> {
                        result.add(
                            OnOffLightDeviceControl(
                                getDeviceControls(
                                    deviceTrait = originalDeviceType.traits().toList(),
                                    supportedTrait = Configure.supportedTraits
                                )
                            )
                        )
                        return@forEach
                    }
                }
            }
        }
    }
    return result
}


fun getDeviceControls(
    deviceTrait: List<Trait>?,
    supportedTrait: List<TraitFactory<out Trait>>
): List<DeviceAttribute> {
    val result = mutableListOf<DeviceAttribute>()
    deviceTrait?.forEach { originalDeviceTrait ->
        supportedTrait.forEach { supportedDeviceTrait ->
            if (originalDeviceTrait.factory.factory == supportedDeviceTrait.factory) {
                when (originalDeviceTrait) {
                    is OnOff ->
                        result.add(OnOffDeviceAttribute())
                    is LevelControl ->
                        result.add(LevelDeviceAttribute())
                }

            }

        }

    }

    return result.toList()

}