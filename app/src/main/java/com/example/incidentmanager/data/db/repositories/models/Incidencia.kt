package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import okhttp3.MultipartBody

@Parcelize
data class Incidencia(
    val id: Int?,
    val titulo: String,
    val descripcion: String,
    val lugar: String,
    val img: String
) : Parcelable


data class IncidenciaRequest(
    val title: String,
    val description: String,
    val classroom: String,
    var state:String,
    var user_id:Int
)
