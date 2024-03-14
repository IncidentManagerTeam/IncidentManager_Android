package com.example.incidentmanager.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.incidentmanager.data.db.repositories.IncidentManagerRepository
import com.example.incidentmanager.data.db.repositories.models.Incidencia
import com.example.incidentmanager.data.db.repositories.models.IncidenciaRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class IncidentFormViewModel @Inject constructor(private val incidentManagerRepository: IncidentManagerRepository):ViewModel(){
    suspend fun createOneIncident(incident: IncidenciaRequest,image: MultipartBody.Part){
        //incidentManagerRepository.updateToken()
        incident.user_id = incidentManagerRepository.userLogged!!.id
        incidentManagerRepository.createIncident(incident,image)
        incidentManagerRepository.getAll()
    }

}