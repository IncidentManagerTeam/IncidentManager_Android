package com.example.incidentmanager.data.db.repositories

import com.example.incidentmanager.data.api.repositories.IncidentManagerApiRepository
import com.example.incidentmanager.data.db.repositories.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentManagerRepository @Inject constructor(
    private val apiRepository: IncidentManagerApiRepository
){
    private var CSRF_TOKEN:String ="";
    private var userLogged:User?=null;
    fun UpdateToken(){
        CSRF_TOKEN = this.apiRepository.getCsrf();
    }
    suspend fun CreateUser(user:User):User?{
        val userCreated = this.apiRepository.register(user)
        if(userCreated)
            userLogged = userCreated
        return userCreated;
    }
    fun LogIn():User?{
        return userLogged
    }
}