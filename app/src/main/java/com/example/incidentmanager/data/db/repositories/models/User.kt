package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class User(
    val id: Int,
    val email: String,
    val nombre: String,
    val apellidos: String,
    val rol: String,
    val clase: String
)
