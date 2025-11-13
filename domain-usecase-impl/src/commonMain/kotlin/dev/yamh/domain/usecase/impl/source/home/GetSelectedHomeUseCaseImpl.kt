package dev.yamh.domain.usecase.impl.source.home

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.repository.source.HomeRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.home.GetHomesUseCase
import dev.yamh.domain.usecase.source.home.GetSelectedHomeUseCase

public class GetSelectedHomeUseCaseImpl(
    private val homeRepository: HomeRepository
) : GetSelectedHomeUseCase {
    override suspend fun invoke(): Result<HomeEntity?> = resultLauncher {
        homeRepository.getSelectedHomes()
    }
}