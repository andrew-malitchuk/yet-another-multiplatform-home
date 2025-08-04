package dev.yamh.io.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.DeviceType
import com.google.home.DeviceTypeFactory
import com.google.home.FactoryRegistry
import com.google.home.Home
import com.google.home.HomeClient
import com.google.home.HomeConfig
import com.google.home.Trait
import com.google.home.TraitFactory
import com.google.home.google.AreaAttendanceState
import com.google.home.google.AreaPresenceState
import com.google.home.google.Assistant
import com.google.home.google.AssistantBroadcast
import com.google.home.google.AssistantFulfillment
import com.google.home.google.GoogleDisplayDevice
import com.google.home.google.GoogleTVDevice
import com.google.home.google.Notification
import com.google.home.google.Time
import com.google.home.google.Volume
import com.google.home.matter.standard.BasicInformation
import com.google.home.matter.standard.BooleanState
import com.google.home.matter.standard.ColorTemperatureLightDevice
import com.google.home.matter.standard.ContactSensorDevice
import com.google.home.matter.standard.DimmableLightDevice
import com.google.home.matter.standard.ExtendedColorLightDevice
import com.google.home.matter.standard.GenericSwitchDevice
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.OccupancySensing
import com.google.home.matter.standard.OccupancySensorDevice
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import com.google.home.matter.standard.OnOffLightSwitchDevice
import com.google.home.matter.standard.OnOffPluginUnitDevice
import com.google.home.matter.standard.OnOffSensorDevice
import com.google.home.matter.standard.RootNodeDevice
import com.google.home.matter.standard.SpeakerDevice
import com.google.home.matter.standard.TemperatureControl
import com.google.home.matter.standard.TemperatureMeasurement
import com.google.home.matter.standard.Thermostat
import com.google.home.matter.standard.ThermostatDevice
import dev.yamh.io.ghome.client.HomeClientEntity
import dev.yamh.io.ghome.core.Configure.Companion.supportedTraits
import dev.yamh.io.ghome.core.Configure.Companion.supportedTypes
import dev.yamh.io.ghome.device.DeviceEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
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