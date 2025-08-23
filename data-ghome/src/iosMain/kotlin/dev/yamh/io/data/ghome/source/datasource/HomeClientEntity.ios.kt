package dev.yamh.io.data.ghome.source.datasource

import dev.yamh.io.data.ghome.source.datasource.home.HomeModel

public actual class HomeClientModel {
    public actual suspend fun getHomes(): List<HomeModel> {
        TODO("Not yet implemented")
    }

    public actual suspend fun getHome(id: Id): HomeModel? {
        TODO("Not yet implemented")
    }

    public actual suspend fun home(id: Id): HomeModel? {
        TODO("Not yet implemented")
    }
}