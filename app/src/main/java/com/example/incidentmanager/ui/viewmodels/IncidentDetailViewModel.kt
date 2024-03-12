package com.example.incidentmanager.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.incidentmanager.data.db.repositories.IncidentManagerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IncidentDetailViewModel @Inject constructor(private val incidentManagerRepository: IncidentManagerRepository): ViewModel(){
    suspend fun deleteIncident(id:Int){
        incidentManagerRepository.deleteIncident(id)
        incidentManagerRepository.getAll()
    }

}
