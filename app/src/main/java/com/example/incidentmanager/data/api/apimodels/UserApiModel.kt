package com.example.incidentmanager.data.api.apimodels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class UserApiModel (
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val course: String,
    val role: String,
    val password: String
)

data class UserModel (
    val name: String,
    val surname: String,
    val email: String,
    val course: String
)