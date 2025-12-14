package dev.yamh.io.presentation.feature.homes.homes.mvi

import dev.yamh.io.presentation.core.platform.source.mvi.MviEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviNavigationEffect
import dev.yamh.io.presentation.core.platform.source.mvi.MviUiEffect

public sealed interface HomesMviEffect : MviEffect

public sealed class HomesNavigationMviEffect : HomesMviEffect, MviNavigationEffect {
    public data object GoToMainMviEffect : HomesNavigationMviEffect()
    public data object GoToSettingsEffect : HomesNavigationMviEffect()
}

public sealed class HomesUiMviEffect : HomesMviEffect, MviUiEffect {
    public data object ShowErrorMessageMviEffect : HomesUiMviEffect()
}