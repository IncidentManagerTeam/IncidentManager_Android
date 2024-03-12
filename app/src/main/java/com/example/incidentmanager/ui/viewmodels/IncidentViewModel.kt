package com.example.incidentmanager.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.incidentmanager.data.db.repositories.IncidentManagerRepository
import com.example.incidentmanager.data.db.repositories.models.Incidencia
import com.example.incidentmanager.data.db.repositories.models.User
import com.example.incidentmanager.databinding.FragmentIncidentBinding
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncidentViewModel @Inject constructor(private val incidentManagerRepository: IncidentManagerRepository):ViewModel(){
    private  var _incidentList: MutableStateFlow<List<Incidencia>> = MutableStateFlow(listOf())
    val incidentList : StateFlow<List<Incidencia>>
        get() {
            return this._incidentList
        }
    suspend  fun getAllIncidents(){
        incidentManagerRepository.getAll()
        _incidentList.value = incidentManagerRepository.incidentList.value
    }
    init {
        this.viewModelScope.launch {
            incidentManagerRepository.getAll()
            _incidentList.value = incidentManagerRepository.incidentList.value
        }
    }
}