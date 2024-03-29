package com.example.incidentmanager.data.db.repositories.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class User(
    val email: String,
    val nombre: String,
    val apellidos: String,
    val clase: String,
    val password: String
)
