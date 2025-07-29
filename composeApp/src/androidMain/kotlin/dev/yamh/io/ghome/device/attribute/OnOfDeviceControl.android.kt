package dev.yamh.io.ghome.device.attribute

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.matter.standard.OnOff
import dev.yamh.io.core.ext.getDevice
import dev.yamh.io.core.ext.getDeviceTrait
import dev.yamh.io.ghome.client.HomeClientEntity
import dev.yamh.io.ghome.device.DeviceEntity
import dev.yamh.io.ghome.device.attribute.base.DeviceAttribute
import kotlinx.coroutines.flow.firstOrNull

actual class OnOffDeviceAttribute : DeviceAttribute() {

    actual companion object {

        val origin = OnOff::class

        @RequiresApi(Build.VERSION_CODES.O_MR1)
        context(HomeClientEntity)
        actual suspend fun DeviceEntity.turnOff() {
            if (!this@DeviceEntity.containsControlAttribute(OnOffDeviceAttribute::class)) return

            (this@HomeClientEntity.nativeHomeClient.getDeviceTrait(
                this@DeviceEntity.id,
                origin
            ) as? OnOff)?.off()
        }

        @RequiresApi(Build.VERSION_CODES.O_MR1)
        context(HomeClientEntity)
        actual suspend fun DeviceEntity.turnOn() {
            if (!this@DeviceEntity.containsControlAttribute(OnOffDeviceAttribute::class)) return

            (this@HomeClientEntity.nativeHomeClient.getDeviceTrait(
                this@DeviceEntity.id,
                origin
            ) as? OnOff)?.on()

        }
    }
}