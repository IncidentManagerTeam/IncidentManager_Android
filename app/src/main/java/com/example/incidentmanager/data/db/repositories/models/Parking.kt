package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Parking(
    val id: Int,
    val matricula: String,
    val nsolicitud: Long,
    val nalumnos: Int,
    val fecha: Date,
    val estado: String
) : Parcelable
