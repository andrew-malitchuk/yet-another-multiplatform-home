package dev.yamh.domain.core.source.model.device.type

public enum class DeviceType {
    OnOff,
    Dimmable,
    ColorControl,
    TemperatureSensor,
    Contact,
    WindowCovering,
}

public enum class DeviceCustomType(public val typeName: String) {
    Water("Water"),
    Door("Door"),
    Move("Move"),
}