package com.example.incidentmanager.data.api.apimodels

data class ParkingApiModel(
    val data:List<ParkingApiData>
)
data class ParkingApiData(
    val id:Int,
    val licensePlate: String,
    val companion: Int,
    val date: String,
    val state: String,
    val user:UserApiModel
)
