package dev.yamh.domain.usecase.impl.source

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.SetLanguageUseCase
import dev.yamh.domain.usecase.source.SetThemeUseCase

public class SetLanguageUseCaseImpl(
    private val settingsRepository: SettingsRepository
) : SetLanguageUseCase {
    override suspend fun invoke(language: LanguageEntity): Optional = resultLauncher{
        settingsRepository.setLanguage(language.lang)
    }
}