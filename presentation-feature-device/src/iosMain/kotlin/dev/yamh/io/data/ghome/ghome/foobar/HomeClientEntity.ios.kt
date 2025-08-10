package dev.yamh.io.data.ghome.ghome.foobar

import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel

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