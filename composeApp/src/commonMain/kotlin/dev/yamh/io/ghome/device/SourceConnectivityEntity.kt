package dev.yamh.io.ghome.device

data class SourceConnectivityEntity(
    val connectivityState: ConnectivityStateEntity,
    val dataSourceLocality: LocalityTypeEntity,
    val backingHubs: Set<String>
)