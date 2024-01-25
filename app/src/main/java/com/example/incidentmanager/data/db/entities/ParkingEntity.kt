package com.example.incidentmanager.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidentmanager.data.db.repositories.models.Parking
import java.util.Date

@Entity(tableName = "parking")
data class ParkingEntity(
    @PrimaryKey
    val id: Int,
    val matricula: String,
    val nsolicitud: Long,
    val nalumnos: Int,
    val fecha: Date,
    val estado: String,
)

fun List<ParkingEntity>.asListParking():List<Parking> {
    return this.map {
        Parking(
            it.id,
            it.matricula,
            it.nsolicitud,
            it.nalumnos,
            it.fecha,
            it.estado
        )
    }
}