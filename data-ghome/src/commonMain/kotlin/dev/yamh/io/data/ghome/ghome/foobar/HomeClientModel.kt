package dev.yamh.io.data.ghome.ghome.foobar

import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

public expect class HomeClientModel {
   public constructor()

    public suspend fun getHomes(): List<HomeModel>
    public fun getHomesFlow(): Flow<List<HomeModel>>
    public suspend fun getHome(id: Id): HomeModel?
    public fun getHomeFlow(id: Id): Flow<HomeModel?>
    public suspend fun home(id: Id): HomeModel?
    public fun homeFlow(id: Id): Flow<HomeModel?>

}