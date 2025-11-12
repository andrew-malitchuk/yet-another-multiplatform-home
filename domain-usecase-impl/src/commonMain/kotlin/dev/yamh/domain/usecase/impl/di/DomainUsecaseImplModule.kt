package dev.yamh.domain.usecase.impl.di

import dev.yamh.domain.usecase.impl.source.SetLanguageUseCaseImpl
import dev.yamh.domain.usecase.impl.source.SetThemeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.SubscribeToLanguageChangesUseCaseImpl
import dev.yamh.domain.usecase.impl.source.SubscribeToThemeChangesUseCaseImpl
import dev.yamh.domain.usecase.impl.source.authorization.AuthorizeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.authorization.CheckAuthorizationUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.ChangeDeviceAttributeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.GetAllDevicesUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.GetDeviceAttributeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.GetDeviceByIdUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.GetDevicesByRoomUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.GetSelectedDeviceUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.IsDeviceSelectedUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.MakeDeviceSelectedUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.SetCustomDeviceTypeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.device.SubscribeToDeviceUpdatesUseCaseImpl
import dev.yamh.domain.usecase.impl.source.home.GetHomesUseCaseImpl
import dev.yamh.domain.usecase.impl.source.home.GetSelectedHomeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.home.SetSelectedHomeUseCaseImpl
import dev.yamh.domain.usecase.impl.source.onboarding.IsOnboardingCompletedUseCaseImpl
import dev.yamh.domain.usecase.impl.source.onboarding.SetOnboardingCompletedUseCaseImpl
import dev.yamh.domain.usecase.impl.source.room.GetRoomsByHomeUseCaseImpl
import dev.yamh.domain.usecase.source.SetLanguageUseCase
import dev.yamh.domain.usecase.source.SetThemeUseCase
import dev.yamh.domain.usecase.source.SubscribeToLanguageChangesUseCase
import dev.yamh.domain.usecase.source.SubscribeToThemeChangesUseCase
import dev.yamh.domain.usecase.source.authorization.AuthorizeUseCase
import dev.yamh.domain.usecase.source.authorization.CheckAuthorizationUseCase
import dev.yamh.domain.usecase.source.device.ChangeDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetAllDevicesUseCase
import dev.yamh.domain.usecase.source.device.GetDeviceAttributeUseCase
import dev.yamh.domain.usecase.source.device.GetDeviceByIdUseCase
import dev.yamh.domain.usecase.source.device.GetDevicesByRoomUseCase
import dev.yamh.domain.usecase.source.device.GetSelectedDeviceUseCase
import dev.yamh.domain.usecase.source.device.IsDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.MakeDeviceSelectedUseCase
import dev.yamh.domain.usecase.source.device.SetCustomDeviceTypeUseCase
import dev.yamh.domain.usecase.source.device.SubscribeToDeviceUpdatesUseCase
import dev.yamh.domain.usecase.source.home.GetHomesUseCase
import dev.yamh.domain.usecase.source.home.GetSelectedHomeUseCase
import dev.yamh.domain.usecase.source.home.SetSelectedHomeUseCase
import dev.yamh.domain.usecase.source.onboarding.IsOnboardingCompletedUseCase
import dev.yamh.domain.usecase.source.onboarding.SetOnboardingCompletedUseCase
import dev.yamh.domain.usecase.source.room.GetRoomsByHomeUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

public val domainUseCaseImplModule: Module = module {

    single<SubscribeToThemeChangesUseCase> {
        SubscribeToThemeChangesUseCaseImpl(
            settingsRepository = get()
        )
    }
    single<SetThemeUseCase> {
        SetThemeUseCaseImpl(
            settingsRepository = get()
        )
    }
    single<SetLanguageUseCase> {
        SetLanguageUseCaseImpl(
            settingsRepository = get()
        )
    }
    single<SubscribeToLanguageChangesUseCase> {
        SubscribeToLanguageChangesUseCaseImpl(
            settingsRepository = get()
        )
    }
    single<GetHomesUseCase> {
        GetHomesUseCaseImpl(
            homeRepository = get(),
            colorRepository = get()
        )
    }
    single<GetSelectedHomeUseCase> {
        GetSelectedHomeUseCaseImpl(
            homeRepository = get()
        )
    }
    single<SetSelectedHomeUseCase> {
        SetSelectedHomeUseCaseImpl(
            homeRepository = get()
        )
    }
    single<GetRoomsByHomeUseCase> {
        GetRoomsByHomeUseCaseImpl(
            roomRepository = get(),
            colorRepository = get(),
            deviceRepository = get()
        )
    }
    single<GetDevicesByRoomUseCase> {
        GetDevicesByRoomUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<SubscribeToDeviceUpdatesUseCase> {
        SubscribeToDeviceUpdatesUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<ChangeDeviceAttributeUseCase> {
        ChangeDeviceAttributeUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<GetDeviceAttributeUseCase> {
        GetDeviceAttributeUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<IsOnboardingCompletedUseCase> {
        IsOnboardingCompletedUseCaseImpl(
            settingsRepository = get()
        )
    }
    single<SetOnboardingCompletedUseCase> {
        SetOnboardingCompletedUseCaseImpl(
            settingsRepository = get()
        )
    }
    single<CheckAuthorizationUseCase> {
        CheckAuthorizationUseCaseImpl(
            authorizationRepository = get()
        )
    }
    single<AuthorizeUseCase> {
        AuthorizeUseCaseImpl(
            authorizationRepository = get()
        )
    }
    single<MakeDeviceSelectedUseCase> {
        MakeDeviceSelectedUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<IsDeviceSelectedUseCase> {
        IsDeviceSelectedUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<SetCustomDeviceTypeUseCase> {
        SetCustomDeviceTypeUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<GetDeviceByIdUseCase> {
        GetDeviceByIdUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<GetAllDevicesUseCase> {
        GetAllDevicesUseCaseImpl(
            deviceRepository = get()
        )
    }
    single<GetSelectedDeviceUseCase> {
        GetSelectedDeviceUseCaseImpl(
            getRoomsByHomeUseCase = get(),
            homeRepository = get(),
            getDeviceAttributeUseCase = get()
        )
    }
}