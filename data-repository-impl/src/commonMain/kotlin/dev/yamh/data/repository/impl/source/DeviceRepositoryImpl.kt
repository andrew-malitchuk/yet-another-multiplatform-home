package dev.yamh.data.repository.impl.source

import dev.yamh.common.core.core.Id
import dev.yamh.data.database.source.DeviceDatabaseSource
import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.attribute.AttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.ColorControlAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.ContactAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.DimmableAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.OnOffAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.TemperatureAttributeEntity
import dev.yamh.domain.core.source.model.device.attribute.WindowCoveringEntity
import dev.yamh.domain.core.source.model.device.toEntity
import dev.yamh.domain.core.source.model.device.toNewEntity
import dev.yamh.domain.core.source.model.device.type.DeviceCustomType
import dev.yamh.domain.core.source.model.device.type.DeviceType
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.io.data.ghome.source.datasource.HomeClientModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.ColorControlAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.ContactAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.DimmableAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.OnOffAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.TemperatureAttributeModel
import dev.yamh.io.data.ghome.source.datasource.device.attribute.WindowCoveringAttributeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class DeviceRepositoryImpl(
    private val homeClient: HomeClientModel,
    private val deviceDatabaseSource: DeviceDatabaseSource
) : DeviceRepository {

    override suspend fun getDevices(): List<DeviceEntity> {
        return homeClient.getDevices().map { it.toNewEntity() }
    }

    override suspend fun getDevicesByRoom(roomId: Id): List<DeviceEntity> {
        return homeClient.getRoom(Id(roomId.value))?.getDevices()?.map {
            it.toNewEntity()
        } ?: emptyList()
    }

    override suspend fun loadDevice(id: Id): DeviceEntity? {
        return deviceDatabaseSource.getAll().firstOrNull { it.id == id.value }?.toEntity()
    }

    override suspend fun linkDevice(device: DeviceEntity, roomId: Id) {
        deviceDatabaseSource.save(
            dev.yamh.data.database.core.model.DeviceDatabaseModel(
                id = device.id.value,
                name = device.name.value,
                roomId = roomId.value,
                type = device.customType?.typeName
            )
        )
    }

    override suspend fun unlinkDevice(device: DeviceEntity, roomId: Id) {
        deviceDatabaseSource.delete(
            dev.yamh.data.database.core.model.DeviceDatabaseModel(
                id = device.id.value,
                name = device.name.value,
                roomId = roomId.value,
                type = device.customType?.typeName
            )
        )
    }

    override suspend fun isDeviceSelected(
        device: DeviceEntity,
        roomId: Id
    ): Boolean {
        return deviceDatabaseSource.getAll().firstOrNull { it.roomId == roomId.value } != null
    }

    override suspend fun loadDevicesByRoom(roomId: Id): List<DeviceEntity> {
        return deviceDatabaseSource.getAll().filter { it.roomId == roomId.value }
            .map { it.toEntity() }
    }

    override suspend fun setCustomType(
        device: DeviceEntity,
        customType: DeviceCustomType
    ) {
        deviceDatabaseSource.save(
            dev.yamh.data.database.core.model.DeviceDatabaseModel(
                id = device.id.value,
                name = device.name.value,
                roomId = device.roomId?.value ?: "",
                type = customType.typeName
            )
        )
    }

    override suspend fun subscribeToChanges(
        device: DeviceEntity,
        type: DeviceType
    ): Flow<AttributeEntity> {
        return homeClient.subscribeAttributeChanges(
            Id(device.id.value),
            dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.entries.firstOrNull {
                it.name == type.name
            }
        ).map {
            when (it) {
                is OnOffAttributeModel -> OnOffAttributeEntity(
                    it.isOn
                )

                is DimmableAttributeModel -> DimmableAttributeEntity(
                    it.level
                )

                is ColorControlAttributeModel -> ColorControlAttributeEntity(
                    ""
                )

                is TemperatureAttributeModel -> TemperatureAttributeEntity(it.temperature)
                is ContactAttributeModel -> ContactAttributeEntity(it.isOpen)
                is WindowCoveringAttributeModel -> WindowCoveringEntity(it.isOpen)
            }
        }
    }

    override suspend fun getDeviceAttribute(
        device: DeviceEntity,
        type: DeviceType
    ): AttributeEntity? {
        return homeClient.getDeviceAttribute(
            Id(device.id.value),
            dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.entries.firstOrNull {
                it.name == type.name
            }
        ).let {
            when (it) {
                is OnOffAttributeModel -> OnOffAttributeEntity(
                    it.isOn
                )

                is DimmableAttributeModel -> DimmableAttributeEntity(
                    it.level
                )

                is ColorControlAttributeModel -> ColorControlAttributeEntity(
                    "foo"
                )

                is TemperatureAttributeModel -> TemperatureAttributeEntity(it.temperature)
                is ContactAttributeModel -> ContactAttributeEntity(it.isOpen)
                else -> null
            }
        }
    }

    override suspend fun changeDeviceAttribute(
        device: DeviceEntity,
        type: DeviceType,
        attribute: AttributeEntity
    ) {
        homeClient.changeDeviceAttribute(
            Id(device.id.value),
            dev.yamh.io.data.ghome.source.datasource.device.type.DeviceType.entries.firstOrNull {
                it.name == type.name
            },
            when (attribute) {
                is OnOffAttributeEntity -> OnOffAttributeModel(
                    attribute.isOn
                )

                is DimmableAttributeEntity -> DimmableAttributeModel(
                    attribute.level
                )

                is ColorControlAttributeEntity -> ColorControlAttributeModel(
                    attribute.hue
                )

                is TemperatureAttributeEntity -> TemperatureAttributeModel(
                    attribute.temperature
                )

                is ContactAttributeEntity -> ContactAttributeModel(
                    attribute.isOpen
                )

                is WindowCoveringEntity -> WindowCoveringAttributeModel(
                    attribute.isOpen
                )
            }
        )
    }

}


