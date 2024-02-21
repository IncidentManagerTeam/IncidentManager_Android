package com.example.incidentmanager.data.api.apimodels

data class RegisterApiModel(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val course: String,
    val role: String,
    val password: String
)

data class RegisterModel(
    val name: String,
    val surname: String,
    val email: String,
    val course: String,
)