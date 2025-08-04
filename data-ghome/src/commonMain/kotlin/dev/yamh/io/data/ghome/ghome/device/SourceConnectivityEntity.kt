package dev.yamh.io.data.ghome.ghome.device

public data class SourceConnectivityEntity(
    val connectivityState: ConnectivityStateEntity,
    val dataSourceLocality: LocalityTypeEntity,
    val backingHubs: Set<String>
)