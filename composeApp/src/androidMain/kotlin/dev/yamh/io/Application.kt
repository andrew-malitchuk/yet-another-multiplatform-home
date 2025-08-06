package dev.yamh.io

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import dev.yamh.io.data.ghome.di.gHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

public class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@Application)
        }
    }
}

public fun initKoin(config: KoinAppDeclaration? = null): KoinApplication =
    startKoin {
        config?.invoke(this)
        modules(
            gHomeModule
        )
    }