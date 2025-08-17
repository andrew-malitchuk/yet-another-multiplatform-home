package dev.yamh.io.data.ghome.ghome.foobar

import com.google.home.HomeClient
import com.google.home.Structure
import dev.yamh.io.data.ghome.core.ext.getStructure
import dev.yamh.io.data.ghome.core.ext.getStructureFlow
import dev.yamh.io.data.ghome.core.ext.getStructures
import dev.yamh.io.data.ghome.core.ext.getStructuresFlow
import dev.yamh.io.data.ghome.ghome.foobar.core.Id
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel
import dev.yamh.io.data.ghome.ghome.foobar.home.HomeModel.Companion.toHomeModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import kotlin.collections.map
import kotlin.getValue

public actual class HomeClientModel : KoinComponent {

    public actual constructor()

    public val nativeHomeClient: HomeClient by inject()

    public actual suspend fun getHomes(): List<HomeModel> {
        return nativeHomeClient.getStructures().map { it.toHomeModel() }
    }

    public actual fun getHomesFlow(): Flow<List<HomeModel>> {
        return nativeHomeClient.getStructuresFlow()
            .map { it.map { structure -> structure.toHomeModel() } }
    }

    public actual suspend fun getHome(id: Id): HomeModel? {
        return nativeHomeClient.getStructure(id.value)?.toHomeModel()
    }

    public actual fun getHomeFlow(id: Id): Flow<HomeModel?> {
        return nativeHomeClient.getStructureFlow(id.value)
            .map { structure -> structure?.toHomeModel() }
    }

    public actual suspend fun home(id: Id): HomeModel? {
        return getHome(id)
    }

    public actual fun homeFlow(id: Id): Flow<HomeModel?> {
        return getHomeFlow(id)
    }

}