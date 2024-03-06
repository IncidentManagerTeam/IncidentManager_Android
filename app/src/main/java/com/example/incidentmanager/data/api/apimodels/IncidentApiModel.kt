package com.example.incidentmanager.data.api.apimodels

data class IncidentApiModel(
    val data:List<IncidentApiData>
)
data class IncidentApiData(
    val id:Int,
    val title: String,
    val description: String,
    val lugar: String,
    val img: String,
    val classroom:String,
    val user:UserApiModel
)
