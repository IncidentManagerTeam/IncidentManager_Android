package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Incidencia(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val lugar: String,
    val img: String
) : Parcelable
