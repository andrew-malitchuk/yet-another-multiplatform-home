package dev.yamh.domain.usecase.source

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.usecase.core.monad.Optional

public interface SetLanguageUseCase {
    public suspend operator fun invoke(language: LanguageEntity): Optional
}