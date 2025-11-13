package dev.yamh.domain.usecase.impl.source

import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.impl.core.util.resultLauncher
import dev.yamh.domain.usecase.source.SubscribeToThemeChangesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class SubscribeToThemeChangesUseCaseImpl(
    private val settingsRepository: SettingsRepository
) : SubscribeToThemeChangesUseCase {
    override fun invoke(): Flow<Result<ThemeEntity>> =
        settingsRepository.themeFlow.map { theme ->
            runCatching {
                ThemeEntity.entries.firstOrNull { it.isDark == theme } ?: ThemeEntity.LIGHT
            }
        }
}