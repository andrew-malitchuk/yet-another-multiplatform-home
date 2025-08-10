package dev.yamh.io.data.ghome.ghome.foobar.device.attribute

import androidx.annotation.RequiresApi
import com.google.home.matter.standard.OnOff
import dev.yamh.io.data.ghome.core.ext.getDeviceTrait
import dev.yamh.io.data.ghome.ghome.foobar.HomeClientModel
import dev.yamh.io.data.ghome.ghome.foobar.device.DeviceModel
import dev.yamh.io.data.ghome.ghome.foobar.device.attribute.base.DeviceAttribute
import kotlin.reflect.KClass

public actual class OnOffDeviceAttribute : DeviceAttribute() {

    public actual companion object {

        public val origin: KClass<*> = OnOff::class

        context(HomeClientModel)
        public actual suspend fun DeviceModel.turnOff() {
            if (!this@DeviceModel.containsControlAttribute(OnOffDeviceAttribute::class)) return

            (this@HomeClientModel.nativeHomeClient.getDeviceTrait(
                this@DeviceModel.id.value,
                origin
            ) as? OnOff)?.off()
        }

        context(HomeClientModel)
        public actual suspend fun DeviceModel.turnOn() {
            if (!this@DeviceModel.containsControlAttribute(OnOffDeviceAttribute::class)) return

            (this@HomeClientModel.nativeHomeClient.getDeviceTrait(
                this@DeviceModel.id.value,
                origin
            ) as? OnOff)?.on()

        }
    }
}