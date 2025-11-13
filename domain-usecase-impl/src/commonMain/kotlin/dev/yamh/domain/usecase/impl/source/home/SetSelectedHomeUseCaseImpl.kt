package dev.yamh.domain.usecase.impl.source.home

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.repository.source.HomeRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.home.GetSelectedHomeUseCase
import dev.yamh.domain.usecase.source.home.SetSelectedHomeUseCase

public class SetSelectedHomeUseCaseImpl(
    private val homeRepository: HomeRepository
) : SetSelectedHomeUseCase {
    override suspend fun invoke(home: HomeEntity?): Optional = resultLauncher {
        homeRepository.setSelectedHomes(home)
    }
}