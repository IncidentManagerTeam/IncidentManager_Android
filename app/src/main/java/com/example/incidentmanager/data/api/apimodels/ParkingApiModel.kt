package com.example.incidentmanager.data.api.apimodels

import com.example.incidentmanager.data.db.repositories.models.Parking

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
){
    fun toParking():Parking{
        return  Parking(
             licensePlate,
             id,
             companion,
             date,
             state
        )
    }
}
