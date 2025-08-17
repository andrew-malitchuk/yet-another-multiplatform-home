package dev.yamh.io.data.ghome.ghome.client

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.DeviceType
import com.google.home.DeviceTypeFactory
import com.google.home.HomeClient
import com.google.home.Trait
import com.google.home.TraitFactory
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import dev.yamh.io.data.ghome.core.ext.getDevice
import dev.yamh.io.data.ghome.core.ext.getDeviceTypes
import dev.yamh.io.data.ghome.core.ext.getDevices
import dev.yamh.io.data.ghome.ghome.core.Configure
import kotlinx.coroutines.flow.firstOrNull
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

//public actual class HomeClientEntity : KoinComponent {
//
//    public val nativeHomeClient: HomeClient by inject()
//
//
//    @RequiresApi(Build.VERSION_CODES.O_MR1)
//    public actual suspend fun getDevices(): List<DeviceEntity> {
//
//        return nativeHomeClient.getDevices().map {
//            DeviceEntity(
//                id = it.id.id,
//                name = it.name,
//                isMatterDevice = it.isMatterDevice,
//                isInStructure = it.isInStructure,
//                isInRoom = it.isInRoom,
//                roomId = it.roomId?.id,
//                structureId = it.structureId?.id,
//                agentId = it.agentId?.id,
//                controls = getDeviceType(
//                    deviceType = it.types().firstOrNull()?.toList(),
//                    supportedType = Configure.supportedTypes
//                )
//            )
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O_MR1)
//    public actual suspend fun requestPermissions(forceLaunch: Boolean): PermissionsResultEntity {
//        val result = nativeHomeClient.requestPermissions(forceLaunch).let { original ->
//            PermissionsResultEntity(
//                status = PermissionsResultStatusEntity.entries
//                    .firstOrNull { it.name == original.status.name }
//                    ?: PermissionsResultStatusEntity.ERROR,
//                errorMessage = original.errorMessage,
//            )
//        }
//        return result
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O_MR1)
//    public actual suspend fun getDevice(id: String): DeviceEntity? {
//        return nativeHomeClient.getDevice(id)?.let {
//            DeviceEntity(
//                id = it.id.id,
//                name = it.name,
//                isMatterDevice = it.isMatterDevice,
//                isInStructure = it.isInStructure,
//                isInRoom = it.isInRoom,
//                roomId = it.roomId?.id,
//                structureId = it.structureId?.id,
//                agentId = it.agentId?.id,
//                controls = getDeviceType(
//                    deviceType = it.types().firstOrNull()?.toList(),
//                    supportedType = Configure.supportedTypes
//                )
//            )
//        }
//    }
//
//    @RequiresApi(Build.VERSION_CODES.O_MR1)
//    public actual suspend fun getDeviceTypes(id: String): List<String> {
//        return nativeHomeClient.getDeviceTypes(id)?.map { it.toString() } ?: emptyList()
//    }
//}
//
//
//public fun getDeviceType(
//    deviceType: List<DeviceType>?,
//    supportedType: List<DeviceTypeFactory<out DeviceType>>
//): List<DeviceControl> {
//    val result = mutableListOf<DeviceControl>()
//    deviceType?.forEach { originalDeviceType ->
//        supportedType.forEach { supportedDeviceType ->
//            if (originalDeviceType.factory.factory::class == supportedDeviceType.factory::class) {
//                when (originalDeviceType) {
//                    is OnOffLightDevice -> {
//                        result.add(
//                            OnOffLightDeviceControl(
//                                getDeviceControls(
//                                    deviceTrait = originalDeviceType.traits().toList(),
//                                    supportedTrait = Configure.supportedTraits
//                                )
//                            )
//                        )
//                        return@forEach
//                    }
//                }
//            }
//        }
//    }
//    return result
//}
//
//
//public fun getDeviceControls(
//    deviceTrait: List<Trait>?,
//    supportedTrait: List<TraitFactory<out Trait>>
//): List<DeviceAttribute> {
//    val result = mutableListOf<DeviceAttribute>()
//    deviceTrait?.forEach { originalDeviceTrait ->
//        supportedTrait.forEach { supportedDeviceTrait ->
//            if (originalDeviceTrait.factory.factory == supportedDeviceTrait.factory) {
//                when (originalDeviceTrait) {
//                    is OnOff ->
//                        result.add(OnOffDeviceAttribute())
//                    is LevelControl ->
//                        result.add(LevelDeviceAttribute())
//                }
//
//            }
//
//        }
//
//    }
//
//    return result.toList()
//
//}