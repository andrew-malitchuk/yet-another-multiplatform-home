package dev.yamh.domain.usecase.impl.source.home

import dev.yamh.common.core.core.Color
import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.repository.source.ColorRepository
import dev.yamh.domain.repository.source.HomeRepository
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.SetLanguageUseCase
import dev.yamh.domain.usecase.source.home.GetHomesUseCase

public class GetHomesUseCaseImpl(
    private val homeRepository: HomeRepository,
    private val colorRepository: ColorRepository,
) : GetHomesUseCase {
    override suspend fun invoke(): Result<List<HomeEntity>> = resultLauncher {
        val remoteHomes = homeRepository.getHomes().sortedBy { it.id.value }
        val localHomes =
            homeRepository.loadHomes().getOrNull()?.sortedBy { it.id.value } ?: emptyList()
        var match = true

        if (remoteHomes.count() != localHomes.count()) {
            match = false
        } else {
            for (i in remoteHomes.indices) {
                if (remoteHomes[i].id != localHomes[i].id) {
                    match = false
                    break
                }
            }
        }

        when (match) {
            true -> {
                localHomes.sortedBy { it.id.value }
                localHomes
            }
            false -> {
                val filledHomes = fulfillData(remoteHomes)
                homeRepository.saveHomes(filledHomes)

                filledHomes.sortedBy { it.id.value }
                filledHomes
            }
        }

    }

    private fun fulfillData(homes: List<HomeEntity>): List<HomeEntity> {
        return homes.map { home ->
            val color = if (home.color == null || home.color?.value.isNullOrEmpty()) {
                colorRepository.colorForKey(home.id.value)
            } else {
                home.color?.value
            }
            home.copy(
                color = Color(color ?: "")
            )
        }
    }
}