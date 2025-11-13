package dev.yamh.domain.usecase.impl.source

import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.core.monad.Optional
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.SetThemeUseCase
import dev.yamh.domain.usecase.source.SubscribeToThemeChangesUseCase

public class SetThemeUseCaseImpl(
    private val settingsRepository: SettingsRepository
) : SetThemeUseCase {
    override suspend fun invoke(theme: ThemeEntity): Optional = resultLauncher{
        settingsRepository.setDarkTheme(theme.isDark)
    }
}