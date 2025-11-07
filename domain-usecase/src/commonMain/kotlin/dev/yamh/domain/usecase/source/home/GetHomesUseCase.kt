package dev.yamh.domain.usecase.source.home

import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.usecase.core.monad.Optional


public interface GetHomesUseCase {
    public suspend operator fun invoke(): Result<List<HomeEntity>>
}