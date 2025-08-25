package dev.yamh.data.preference.source

public interface HomePreferenceSource {
    public suspend fun getSelectedHome(): String?
    public suspend fun setSelectedHome(value: String)

}