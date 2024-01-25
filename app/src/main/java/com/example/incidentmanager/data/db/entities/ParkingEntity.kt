package com.example.incidentmanager.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidentmanager.data.db.repositories.models.Parking
import java.util.Date

@Entity(tableName = "parking")
data class ParkingEntity(
    val matricula: String,
    @PrimaryKey(autoGenerate = true)
    val nsolicitud: Long,
    val nalumnos: Int,
    val fecha: String,
    val estado: String,
)

fun List<ParkingEntity>.asListParking():List<Parking> {
    return this.map {
        Parking(
            it.matricula,
            it.nsolicitud,
            it.nalumnos,
            it.fecha,
            it.estado
        )
    }
}