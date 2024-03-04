package com.example.incidentmanager.ui.fragments.user

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.incidentmanager.data.db.repositories.IncidentManagerRepository
import com.example.incidentmanager.data.db.repositories.models.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val incidentManagerRepository:IncidentManagerRepository):ViewModel(){
    private  var CurrentUser: MutableStateFlow<User> = MutableStateFlow(User("","","","","","") );
    val _staticCurrentUser : StateFlow<User>
        get() {
            return this.CurrentUser
        }

    fun checkLoginStatus(): Boolean {
        val user = incidentManagerRepository.logIn()
        if (user != null) {
            return true
        }
        return false
    }
    suspend  fun logIn(){
        var tokken = incidentManagerRepository.updateToken()
        Log.d("TOKKEN", "El tokken es: $tokken")
    }
}