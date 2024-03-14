package com.example.incidentmanager.data.api.apimodels

import com.example.incidentmanager.data.db.repositories.models.Incidencia


fun List<IncidentApiData>.toIncidenciaList(): List<Incidencia> {
    return map { incident ->
        Incidencia(
            incident.id,
            incident.title,
            incident.description,
            incident.classroom,
            incident.image
        )
    }
}

data class IncidentApiData(
    val id: Int,
    val image: String,
    val description: String,
    val state: String,
    val user: UserApiModel,
    val classroom: String,
    val title: String
) {
    fun toIncidencia(): Incidencia {
        return Incidencia(
            id,
            title,
            description,
            classroom,
            image
        )
    }
}




