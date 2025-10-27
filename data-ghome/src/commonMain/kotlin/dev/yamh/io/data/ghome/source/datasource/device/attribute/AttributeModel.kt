package dev.yamh.io.data.ghome.source.datasource.device.attribute

public sealed class AttributeModel

public data class OnOffAttributeModel(
    val isOn: Boolean
) : AttributeModel()

public data class DimmableAttributeModel(
    val level: Int
) : AttributeModel()

public data class ColorControlAttributeModel(
    val hue:String=""
) : AttributeModel()

public data class TemperatureAttributeModel(
    val temperature: String="",
) : AttributeModel()

public data class ContactAttributeModel(
    val isOpen: Boolean
) : AttributeModel()

public data class WindowCoveringAttributeModel(
    val isOpen: Boolean
) : AttributeModel()