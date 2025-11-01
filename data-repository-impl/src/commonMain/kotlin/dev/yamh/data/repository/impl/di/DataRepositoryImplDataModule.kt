package dev.yamh.data.repository.impl.di

import dev.yamh.data.repository.impl.source.AuthorizationRepositoryImpl
import dev.yamh.data.repository.impl.source.ColorRepositoryImpl
import dev.yamh.data.repository.impl.source.DeviceRepositoryImpl
import dev.yamh.data.repository.impl.source.HomeRepositoryImpl
import dev.yamh.data.repository.impl.source.RoomRepositoryImpl
import dev.yamh.data.repository.impl.source.SettingsRepositoryImpl
import dev.yamh.domain.repository.source.AuthorizationRepository
import dev.yamh.domain.repository.source.ColorRepository
import dev.yamh.domain.repository.source.DeviceRepository
import dev.yamh.domain.repository.source.HomeRepository
import dev.yamh.domain.repository.source.RoomRepository
import dev.yamh.domain.repository.source.SettingsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

public val dataRepositoryImplDataModule: Module = module {
    single<SettingsRepository> {
        SettingsRepositoryImpl(
            get(),
        )
    }
    single<HomeRepository> {
        HomeRepositoryImpl(
            get(),
            get(),
            get(),
        )
    }
    single<RoomRepository> {
        RoomRepositoryImpl(
            get(),
            get(),
        )
    }
    single<DeviceRepository> {
        DeviceRepositoryImpl(
            get(),
            get(),
        )
    }
    single<AuthorizationRepository> {
        AuthorizationRepositoryImpl()
    }
    single<ColorRepository> {
        ColorRepositoryImpl()
    }
}