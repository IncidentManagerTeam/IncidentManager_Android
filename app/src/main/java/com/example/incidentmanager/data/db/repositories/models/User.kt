package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int,
    val username: String,
    val nombre: String,
    val apellidos: String,
    val rol: String,
    val clase: String
) : Parcelable
