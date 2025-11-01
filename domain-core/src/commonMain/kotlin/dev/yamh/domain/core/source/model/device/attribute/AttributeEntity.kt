package dev.yamh.domain.core.source.model.device.attribute

import kotlinx.serialization.Serializable

@Serializable
public sealed class AttributeEntity

@Serializable
public data class OnOffAttributeEntity(
    val isOn: Boolean
) : AttributeEntity()

@Serializable
public data class DimmableAttributeEntity(
    val level: Int
) : AttributeEntity()

@Serializable
public data class ColorControlAttributeEntity(
    val hue: String
) : AttributeEntity()

@Serializable
public data class TemperatureAttributeEntity(
    val temperature: String
) : AttributeEntity()

@Serializable
public data class ContactAttributeEntity(
    val isOpen: Boolean
) : AttributeEntity()

@Serializable
public data class WindowCoveringEntity(
    val isOpen: Boolean
) : AttributeEntity()