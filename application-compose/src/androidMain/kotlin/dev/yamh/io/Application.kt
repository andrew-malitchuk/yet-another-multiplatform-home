package dev.yamh.io

import android.app.Application
import dev.yamh.io.di.initKoin
import org.koin.android.ext.koin.androidContext

public class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@Application)
        }
    }
}

