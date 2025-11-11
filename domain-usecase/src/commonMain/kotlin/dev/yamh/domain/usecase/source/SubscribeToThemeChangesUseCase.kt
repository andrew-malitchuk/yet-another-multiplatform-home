package dev.yamh.domain.usecase.source

import dev.yamh.domain.core.source.model.ThemeEntity

public interface SubscribeToThemeChangesUseCase {
    public operator fun invoke(): kotlinx.coroutines.flow.Flow<Result<ThemeEntity>>
}