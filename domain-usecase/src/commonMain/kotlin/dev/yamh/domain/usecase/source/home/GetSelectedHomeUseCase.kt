package dev.yamh.domain.usecase.source.home

import dev.yamh.domain.core.source.model.home.HomeEntity

public interface GetSelectedHomeUseCase {
    public suspend operator fun invoke(): Result<HomeEntity?>
}