package dev.yamh.domain.usecase.impl.source.device

import dev.yamh.domain.core.source.model.device.DeviceEntity
import dev.yamh.domain.core.source.model.device.toDeviceType
import dev.yamh.domain.repository.source.HomeRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetSelectedDeviceUseCase
import dev.yamh.domain.usecase.source.room.GetRoomsByHomeUseCase


public class GetSelectedDeviceUseCaseImpl(
    private val getRoomsByHomeUseCase: GetRoomsByHomeUseCase,
    private val homeRepository: HomeRepository,
    private val getDeviceAttributeUseCase: GetDeviceAttributeUseCase
) : GetSelectedDeviceUseCase {
    override suspend fun invoke(): Result<List<DeviceEntity>> = resultLauncher {
        val selectedHome = homeRepository.getSelectedHomes()

        when (selectedHome) {
            null -> emptyList()
            else -> {
                (getRoomsByHomeUseCase(selectedHome).getOrNull() ?: emptyList())
                    .flatMap { room -> room.selectedDevices }
                    .map { device ->
                        if (device.generalType()?.toDeviceType() != null) {

                            device.copy(
                                attribute = getDeviceAttributeUseCase(
                                    device,
                                    device.generalType()?.toDeviceType()!!
                                ).getOrNull()
                            )
                        } else {
                            device
                        }
                    }
            }
        }
    }
}
