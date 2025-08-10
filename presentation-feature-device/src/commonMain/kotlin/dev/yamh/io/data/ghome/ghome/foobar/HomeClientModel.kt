package dev.yamh.io.data.ghome.ghome.foobar

import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel

public expect class HomeClientModel {
   public constructor()

    public suspend fun getHomes(): List<HomeModel>
    public suspend fun getHome(id: Id): HomeModel?
    public suspend fun home(id: Id): HomeModel?

}