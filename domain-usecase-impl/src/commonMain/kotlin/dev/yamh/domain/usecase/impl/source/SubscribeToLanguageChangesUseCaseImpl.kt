package dev.yamh.domain.usecase.impl.source

import dev.yamh.domain.core.source.model.LanguageEntity
import dev.yamh.domain.core.source.model.ThemeEntity
import dev.yamh.domain.repository.source.SettingsRepository
import dev.yamh.domain.usecase.source.SubscribeToLanguageChangesUseCase
import dev.yamh.domain.usecase.source.SubscribeToThemeChangesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public class SubscribeToLanguageChangesUseCaseImpl(
    private val settingsRepository: SettingsRepository
) : SubscribeToLanguageChangesUseCase {
    override fun invoke(): Flow<Result<LanguageEntity>> =
        settingsRepository.languageFlow.map { lang ->
            runCatching {
                LanguageEntity.entries.firstOrNull { it.lang == lang } ?: LanguageEntity.ENGLISH
            }
        }
}