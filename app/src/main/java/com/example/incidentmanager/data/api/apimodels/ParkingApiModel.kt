package com.example.incidentmanager.data.api.apimodels

data class ParkingApiModel(
    val matricula: String,
    val nsolicitud: Long,
    val nalumnos: Int,
    val fecha: String,
    val estado: String
)
