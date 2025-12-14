package dev.yamh.io.presentation.feature.homes.homes.mvi

import dev.yamh.domain.core.source.model.home.HomeEntity
import dev.yamh.io.presentation.core.platform.source.mvi.MviIntent

public sealed class HomesIntent : MviIntent {
    public data object Setup : HomesIntent()
    public data object GoToMainIntent : HomesIntent()
    public data object GoToSettingsIntent : HomesIntent()
    public data object RefreshIntent : HomesIntent()
    public data class OnSelectHomeIntent(val home: HomeEntity) : HomesIntent()
}