package dev.yamh.io.data.ghome.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.FactoryRegistry
import com.google.home.Home
import com.google.home.HomeClient
import com.google.home.HomeConfig
import dev.yamh.io.data.ghome.ghome.core.Configure.Companion.supportedTraits
import dev.yamh.io.data.ghome.ghome.core.Configure.Companion.supportedTypes
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O_MR1)
public actual val gHomeModule: Module = module {

    single<FactoryRegistry> {
        FactoryRegistry(
            types = supportedTypes,
            traits = supportedTraits
        )
    }

    single<HomeClient> {

        val registry: FactoryRegistry = get()

        val config = HomeConfig(
            coroutineContext = Dispatchers.IO,
            factoryRegistry = registry
        )
        val context: Context = get()
        Home.getClient(context = context, homeConfig = config)
    }

}