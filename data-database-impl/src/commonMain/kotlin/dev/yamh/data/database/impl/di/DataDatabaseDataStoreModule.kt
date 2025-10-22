package dev.yamh.data.database.impl.di

import dev.yamh.data.database.impl.core.configure.DatabaseConfigure.DATABASE_NAME
import dev.yamh.data.database.impl.source.DeviceDatabaseSourceImpl
import dev.yamh.data.database.impl.source.HomeDatabaseSourceImpl
import dev.yamh.data.database.impl.source.RoomDatabaseSourceImpl
import dev.yamh.data.database.source.DeviceDatabaseSource
import dev.yamh.data.database.source.HomeDatabaseSource
import dev.yamh.data.database.source.RoomDatabaseSource
import kotbase.Database
import kotbase.DatabaseConfigurationFactory
import kotbase.newConfig
import org.koin.core.module.Module
import org.koin.dsl.module

public val dataDatabaseDataStoreModule: Module = module {

    single<Database> {
        Database(
            DATABASE_NAME,
            DatabaseConfigurationFactory.newConfig()
        )
    }

    single<HomeDatabaseSource> {
        HomeDatabaseSourceImpl(
            get(),
        )
    }

    single<RoomDatabaseSource> {
        RoomDatabaseSourceImpl(
            get(),
        )
    }

    single<DeviceDatabaseSource> {
        DeviceDatabaseSourceImpl(
            get(),
        )
    }

}