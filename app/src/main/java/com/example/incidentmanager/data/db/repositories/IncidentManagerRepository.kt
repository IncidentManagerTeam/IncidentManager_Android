package com.example.incidentmanager.data.db.repositories

import android.util.Base64
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.incidentmanager.data.api.apimodels.UserApiModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.api.apimodels.UserModel
import com.example.incidentmanager.data.api.apimodels.toIncidenciaList
import com.example.incidentmanager.data.api.repositories.IncidentManagerApiRepository
import com.example.incidentmanager.data.db.repositories.models.Incidencia
import com.example.incidentmanager.data.db.repositories.models.IncidenciaRequest
import com.example.incidentmanager.data.db.repositories.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentManagerRepository @Inject constructor(
    private val apiRepository: IncidentManagerApiRepository
){
    private var csrfToken: String = ""
    public var userLogged: UserApiModel? = null
    private var authorization:String="";
    private  val _incidentList: MutableStateFlow<List<Incidencia>> = MutableStateFlow(listOf())
    val incidentList : StateFlow<List<Incidencia>>
        get() {
            return this._incidentList
        }

    suspend fun updateToken() {
        // Manejar la operación de red en un hilo de fondo
        withContext(Dispatchers.IO) {
            csrfToken = apiRepository.getCsrf().toString()
        }
    }

    suspend fun createUser(user: UserModel): UserApiModel? {
        return try {
            val userCreated = apiRepository.postRegister(user)
            userLogged = userCreated
            userCreated
        } catch (e: Exception) {
            // Manejar el error adecuadamente, por ejemplo, registrar o lanzar una excepción
            null
        }
    }

    suspend  fun logIn(user:UserLogin): UserApiModel? {
        userLogged = apiRepository.login(user)
        if(user != null) {
            userLogged?.password = user.password;
            var credentials = "${userLogged?.email}:${userLogged?.password}"
            val credentialsEn = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
            authorization = "Basic $credentialsEn";
        }
        return userLogged;
    }
    suspend fun getAll(){
        val newList = apiRepository.getAllIncident(authorization).toIncidenciaList()
        _incidentList.value =  newList
    }

    suspend fun createIncident(incident: IncidenciaRequest,image: MultipartBody.Part) {
        apiRepository.postIncident(authorization,incident,image)
    }

    suspend fun deleteIncident(id: Int) {
        apiRepository.deleteIncident(authorization,id)
    }

    suspend fun register(user: UserModel): UserApiModel? {
        var user = apiRepository.postRegister(user)
        if(  user != null )
            return user
        return null
    }

}


