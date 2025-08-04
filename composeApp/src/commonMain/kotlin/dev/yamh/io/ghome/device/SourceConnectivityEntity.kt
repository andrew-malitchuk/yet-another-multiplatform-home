package dev.yamh.io.ghome.device

public data class SourceConnectivityEntity(
    val connectivityState: ConnectivityStateEntity,
    val dataSourceLocality: LocalityTypeEntity,
    val backingHubs: Set<String>
)