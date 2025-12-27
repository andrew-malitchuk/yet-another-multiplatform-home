package dev.yamh.io.presentation.feature.room.di

import dev.yamh.io.presentation.feature.room.room.RoomViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

public val presentationFeatureRoom: Module = module {
    single<RoomViewModel> { RoomViewModel(get(), get()) }
}