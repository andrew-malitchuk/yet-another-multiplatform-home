package dev.yamh.domain.usecase.source

import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.usecase.core.monad.Optional

public interface SetThemeUseCase {
    public suspend operator fun invoke(theme: ThemeEntity): Optional
}