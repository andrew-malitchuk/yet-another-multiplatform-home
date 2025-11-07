package dev.yamh.domain.usecase.source.home

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.domain.usecase.core.monad.Optional

public interface SetSelectedHomeUseCase {
    public suspend operator fun invoke(home: HomeEntity?): Optional
}