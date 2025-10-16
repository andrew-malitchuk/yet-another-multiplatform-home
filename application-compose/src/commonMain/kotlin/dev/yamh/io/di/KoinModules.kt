package dev.yamh.io.di

import dev.yamh.data.database.impl.di.dataDatabaseDataStoreModule
import dev.yamh.data.preference.impl.di.dataPreferenceDataStoreModule
import dev.yamh.data.preference.impl.di.dataPreferenceImplModule
import dev.yamh.data.repository.impl.di.dataRepositoryImplDataModule
import dev.yamh.domain.repository.di.domainRepositoryModule
import dev.yamh.domain.usecase.impl.di.domainUseCaseImplModule
import dev.yamh.io.data.ghome.di.dataGHomeModule
import dev.yamh.io.presentation.core.platform.di.presentationCorePlatform
import dev.yamh.io.presentation.feature.authorization.di.presentationFeatureAuthorization
import dev.yamh.io.presentation.feature.device.di.presentationFeatureDevice
import dev.yamh.io.presentation.feature.homes.di.presentationFeatureHomes
import dev.yamh.io.presentation.feature.main.di.presentationFeatureMain
import dev.yamh.io.presentation.feature.onboarding.di.presentationFeatureOnboarding
import dev.yamh.io.presentation.feature.room.di.presentationFeatureRoom
import dev.yamh.io.presentation.feature.settings.di.presentationFeatureSettings
import dev.yamh.io.presentation.feature.splash.di.presentationFeatureSplash
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

public fun initKoin(config: KoinAppDeclaration? = null): KoinApplication =
    startKoin {
        config?.invoke(this)
        modules(
            dataGHomeModule,

            dataPreferenceImplModule,
            dataPreferenceDataStoreModule,
            dataDatabaseDataStoreModule,
            dataRepositoryImplDataModule,
            domainRepositoryModule,
            domainUseCaseImplModule,
            presentationCorePlatform,
            presentationFeatureOnboarding,
            presentationFeatureAuthorization,
            presentationFeatureHomes,
            presentationFeatureSplash,
            presentationFeatureRoom,
            presentationFeatureDevice,
            presentationFeatureMain,
            presentationFeatureSettings,
        )
    }