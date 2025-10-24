package dev.yamh.io.data.ghome.source.core

import com.google.home.DeviceType
import com.google.home.DeviceTypeFactory
import com.google.home.Trait
import com.google.home.TraitFactory
import com.google.home.google.AreaAttendanceState
import com.google.home.google.AreaPresenceState
import com.google.home.google.Assistant
import com.google.home.google.AssistantBroadcast
import com.google.home.google.AssistantFulfillment
import com.google.home.google.GoogleAirCoolerDevice
import com.google.home.google.GoogleDisplayDevice
import com.google.home.google.GoogleTVDevice
import com.google.home.google.Notification
import com.google.home.google.Time
import com.google.home.google.Volume
import com.google.home.matter.standard.AirPurifierDevice
import com.google.home.matter.standard.AirQualitySensorDevice
import com.google.home.matter.standard.BasicInformation
import com.google.home.matter.standard.BooleanState
import com.google.home.matter.standard.ColorControl
import com.google.home.matter.standard.ColorTemperatureLightDevice
import com.google.home.matter.standard.ContactSensorDevice
import com.google.home.matter.standard.DimmableLightDevice
import com.google.home.matter.standard.DoorLockDevice
import com.google.home.matter.standard.GenericSwitchDevice
import com.google.home.matter.standard.LevelControl
import com.google.home.matter.standard.OccupancySensing
import com.google.home.matter.standard.OccupancySensorDevice
import com.google.home.matter.standard.OnOff
import com.google.home.matter.standard.OnOffLightDevice
import com.google.home.matter.standard.OnOffLightSwitchDevice
import com.google.home.matter.standard.OnOffPluginUnitDevice
import com.google.home.matter.standard.OnOffSensorDevice
import com.google.home.matter.standard.RoomAirConditionerDevice
import com.google.home.matter.standard.RootNodeDevice
import com.google.home.matter.standard.SpeakerDevice
import com.google.home.matter.standard.TemperatureControl
import com.google.home.matter.standard.TemperatureMeasurement
import com.google.home.matter.standard.TemperatureSensorDevice
import com.google.home.matter.standard.Thermostat
import com.google.home.matter.standard.ThermostatDevice
import com.google.home.matter.standard.WaterLeakDetectorDevice
import com.google.home.matter.standard.WindowCovering
import com.google.home.matter.standard.WindowCoveringDevice

public class Configure {
    public companion object {
        // List of supported device types by this app:
        public val supportedTypes: List<DeviceTypeFactory<out DeviceType>> = listOf(
            ContactSensorDevice,
            ColorTemperatureLightDevice,
            DimmableLightDevice,
            AirPurifierDevice,
            GenericSwitchDevice,
            GoogleDisplayDevice,
            GoogleTVDevice,
            OccupancySensorDevice,
            OnOffLightDevice,
            OnOffLightSwitchDevice,
            OnOffPluginUnitDevice,
            OnOffSensorDevice,
            RootNodeDevice,
            SpeakerDevice,
            ThermostatDevice,
//            GoogleAirCoolerDevice,
//            RoomAirConditionerDevice,
            TemperatureSensorDevice,
            WaterLeakDetectorDevice,
            DoorLockDevice,
            AirQualitySensorDevice,
            WindowCoveringDevice
        )

        // List of supported device traits by this app:
        public val supportedTraits: List<TraitFactory<out Trait>> = listOf(
            AreaAttendanceState,
            AreaPresenceState,
            Assistant,
            AssistantBroadcast,
            AssistantFulfillment,
            BasicInformation,
            BooleanState,
            OccupancySensing,
            OnOff,
            Notification,
            LevelControl,
            TemperatureControl,
            TemperatureMeasurement,
            Thermostat,
            Time,
            Volume,
            ColorControl,
            WindowCovering
        )
    }
}