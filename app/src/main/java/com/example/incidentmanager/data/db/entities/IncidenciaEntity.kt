package com.example.incidentmanager.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidentmanager.data.db.repositories.models.Incidencia

//incidencia entity 1
@Entity(tableName = "incidencia")
data class IncidenciaEntity(
    @PrimaryKey
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val lugar: String,
    val img: String
)

fun List<IncidenciaEntity>.asListIncidencia():List<Incidencia> {
    return this.map {
        Incidencia(
            it.id,
            it.titulo,
            it.descripcion,
            it.lugar,
            it.img
        )
    }
}