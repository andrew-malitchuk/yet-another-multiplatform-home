package dev.yamh.io

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import dev.yamh.io.di.gHomeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

class Application : Application() {

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    override fun onCreate() {
        super.onCreate()
        initKoin{
            androidContext(this@Application)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O_MR1)
fun initKoin(config: KoinAppDeclaration? = null) =
    startKoin {
        config?.invoke(this)
        modules(
            gHomeModule
        )
    }