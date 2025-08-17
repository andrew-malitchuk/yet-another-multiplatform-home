package dev.yamh.io.data.ghome.ghome.foobar

import com.google.home.HomeClient
import com.google.home.Structure
import dev.yamh.io.data.ghome.core.ext.getStructure
import dev.yamh.io.data.ghome.core.ext.getStructures
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel.Companion.toHomeModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.getValue

public actual class HomeClientModel : KoinComponent {

   public actual constructor()

    public val nativeHomeClient: HomeClient by inject()

    public actual suspend fun getHomes(): List<HomeModel> {
        return nativeHomeClient.getStructures().map { it.toHomeModel() }

    }

    public actual suspend fun getHome(id: Id): HomeModel? {
        return nativeHomeClient.getStructure(id.value)?.toHomeModel()
    }

    public actual suspend fun home(id: Id): HomeModel? {
        return getHome(id)
    }

}