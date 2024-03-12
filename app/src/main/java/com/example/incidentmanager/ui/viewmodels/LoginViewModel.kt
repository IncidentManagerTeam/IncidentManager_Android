package com.example.incidentmanager.ui.fragments.user

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
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
        if (CurrentUser.value.email != "") {
            return true
        }
        return false
    }
    suspend  fun logIn(user:UserLogin):User?{
        var userAcc = incidentManagerRepository.logIn(user);
        if(userAcc != null){
            //incidentManagerRepository.updateToken()
            CurrentUser.value = userAcc.toUser();
            return userAcc.toUser();
        }
        else{
            return null;
        }
    }
}