package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date


data class Parking(
    val matricula: String,
    val nsolicitud: Long,
    val nalumnos: Int,
    val fecha: String,
    val estado: String
)

