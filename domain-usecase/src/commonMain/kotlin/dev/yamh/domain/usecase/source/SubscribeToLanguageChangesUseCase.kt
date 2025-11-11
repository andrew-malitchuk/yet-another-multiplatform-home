package dev.yamh.domain.usecase.source

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity

public interface SubscribeToLanguageChangesUseCase {
    public operator fun invoke(): kotlinx.coroutines.flow.Flow<Result<LanguageEntity>>
}