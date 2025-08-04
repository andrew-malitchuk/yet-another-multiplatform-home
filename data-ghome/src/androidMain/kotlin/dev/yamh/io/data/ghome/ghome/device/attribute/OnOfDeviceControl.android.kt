package dev.yamh.io.data.ghome.ghome.device.attribute

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.home.matter.standard.OnOff
import dev.yamh.io.data.ghome.core.ext.getDeviceTrait
import dev.yamh.io.data.ghome.ghome.client.HomeClientEntity
import dev.yamh.io.data.ghome.ghome.device.DeviceEntity
import dev.yamh.io.data.ghome.ghome.device.attribute.base.DeviceAttribute
import kotlin.reflect.KClass

public actual class OnOffDeviceAttribute : DeviceAttribute() {

    public actual companion object {

        public val origin: KClass<*> = OnOff::class

        @RequiresApi(Build.VERSION_CODES.O_MR1)
        context(HomeClientEntity)
        public actual suspend fun DeviceEntity.turnOff() {
            if (!this@DeviceEntity.containsControlAttribute(OnOffDeviceAttribute::class)) return

            (this@HomeClientEntity.nativeHomeClient.getDeviceTrait(
                this@DeviceEntity.id,
                origin
            ) as? OnOff)?.off()
        }

        @RequiresApi(Build.VERSION_CODES.O_MR1)
        context(HomeClientEntity)
        public actual suspend fun DeviceEntity.turnOn() {
            if (!this@DeviceEntity.containsControlAttribute(OnOffDeviceAttribute::class)) return

            (this@HomeClientEntity.nativeHomeClient.getDeviceTrait(
                this@DeviceEntity.id,
                origin
            ) as? OnOff)?.on()

        }
    }
}