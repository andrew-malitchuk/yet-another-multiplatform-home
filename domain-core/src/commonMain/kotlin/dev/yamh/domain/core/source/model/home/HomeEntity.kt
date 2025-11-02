package dev.yamh.domain.core.source.model.home

import dev.yamh.common.core.core.Color
import dev.yamh.common.core.core.Id
import dev.yamh.common.core.core.Name
import dev.yamh.data.database.core.model.HomeDatabaseModel
import dev.yamh.io.data.ghome.source.datasource.home.HomeModel
import kotlinx.serialization.Serializable

@Serializable
public data class HomeEntity(val id: Id, val name: Name, val color: Color?) {
    public companion object {
        public fun HomeModel.toEntity(): HomeEntity {
            return HomeEntity(
                Id(this.id.value),
                Name(this.name.value),
                Color(this.color.value)
            )
        }

        public fun HomeDatabaseModel.toEntity(): HomeEntity {
            return HomeEntity(
                Id(this.id),
                Name(this.name),
                Color(this.color)
            )
        }

        public fun HomeEntity.toDatabaseModel(): HomeDatabaseModel {
            return HomeDatabaseModel(
                id = this.id.value,
                name = this.name.value,
                selected = false,
                color = this.color?.value ?: ""
            )
        }
    }
}
