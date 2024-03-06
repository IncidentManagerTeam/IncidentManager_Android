package com.example.incidentmanager.data.db.repositories

import android.util.Base64
import com.example.incidentmanager.data.api.apimodels.UserApiModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.api.apimodels.UserModel
import com.example.incidentmanager.data.api.repositories.IncidentManagerApiRepository
import com.example.incidentmanager.data.db.repositories.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IncidentManagerRepository @Inject constructor(
    private val apiRepository: IncidentManagerApiRepository
){
    private var csrfToken: String = ""
    private var userLogged: UserApiModel? = null
    private var authorization:String="";

    suspend fun updateToken() {
        // Manejar la operación de red en un hilo de fondo
        withContext(Dispatchers.IO) {
            csrfToken = apiRepository.getCsrf().toString()
        }
    }

    suspend fun createUser(user: UserModel, csrf: String): UserApiModel? {
        return try {
            val userCreated = apiRepository.postRegister(user, csrf)
            userLogged = userCreated
            userCreated
        } catch (e: Exception) {
            // Manejar el error adecuadamente, por ejemplo, registrar o lanzar una excepción
            null
        }
    }

    suspend  fun logIn(user:UserLogin): UserApiModel? {
        val user = apiRepository.login(user)
        if(user != null) {
            val credentials = "${user?.email}:${user?.password}"
            val credentialsEn = Base64.encodeToString(credentials.toByteArray(), Base64.NO_WRAP)
            authorization = credentialsEn;
        }
        return user;
    }
}
