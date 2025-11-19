package dev.yamh.io.presentation.core.platform.di

import dev.yamh.io.presentation.core.platform.core.util.Platform
import dev.yamh.io.presentation.core.platform.core.util.PlatformImpl
import org.koin.core.module.Module
import org.koin.dsl.module

public actual val presentationCorePlatform: Module = module {

    single<Platform> {
        PlatformImpl(get())
    }

}