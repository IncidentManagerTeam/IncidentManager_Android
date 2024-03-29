package com.example.incidentmanager.data.api.repositories

import android.util.Base64
import com.example.incidentmanager.data.api.apimodels.UserApiModel
import com.example.incidentmanager.data.api.services.UserService
import android.util.Log
import com.example.incidentmanager.data.api.apimodels.CsrfApiModel
import com.example.incidentmanager.data.api.apimodels.IncidentApiData
import com.example.incidentmanager.data.api.apimodels.ParkingApiModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.api.apimodels.UserModel
import com.example.incidentmanager.data.db.repositories.models.IncidenciaRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

import javax.inject.Inject

class IncidentManagerApiRepository @Inject constructor(
    private val userService: UserService,
) {
    suspend fun getAllUser(authorization:String): List<UserApiModel> {
        try {
            val userList = userService.api.getAllUser(authorization)
            return userList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de usuarios", e)
            return emptyList()
        }
    }

    suspend fun getOneUser(id:Int,authorization:String): UserApiModel? {
        try {
            val user = userService.api.getOneUser(authorization,id)
            if (user!=null) {
                return user;
            }
            return null
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de usuarios", e)
            return null
        }
    }

    suspend fun postRegister(user: UserModel): UserApiModel? {
        return withContext(Dispatchers.IO) {
            try {
                val newUser = userService.api.register(user)
                newUser
            } catch (e: Exception) {
                Log.e("Error", "Error al registrar un nuevo usuario", e)
                null
            }
        }
    }
    suspend fun login(userLogin: UserLogin):UserApiModel?{
        return withContext(Dispatchers.IO){
            try {
                /*var credentials = userLogin.email + ":" + userLogin.password
                var encriptedUser= Base64.encodeToString(credentials.toByteArray(),Base64.NO_WRAP)
                var incident = incidentService.api.getAllIncidents(encriptedUser)*/
                //var token = csrfService.api.getCsrf().token
                val user = userService.api.logIn(userLogin)
                 user
            }catch ( e : Exception ){
                var excepcion = e;
                Log.e("Error", "Error al hacer el login con la excepcion : $e")
                null
            }
        }
    }
    suspend fun getAllIncident(authorization:String): List<IncidentApiData> {
        try {
            val incidentList = userService.api.getAllIncidents(authorization)
            return incidentList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de incidencias", e)
            return listOf()
        }
    }

    suspend fun getAllParking(authorization:String):ParkingApiModel {
        try {
            val parkingList = userService.api.getAllParkings(authorization)
            return parkingList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de parkings", e)
            return ParkingApiModel(emptyList())
        }
    }

    suspend fun getCsrf(): CsrfApiModel? {
        try {
            val csrf = userService.api.getCsrf()
            return csrf
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener el csrf", e)
            return null
        }
    }

    suspend fun postIncident(authorization:String,incidentRequest: IncidenciaRequest,image: MultipartBody.Part) {
        try {
            var request = userService.api.postIncident(authorization,incidentRequest,image)
        }catch (e:Exception){
            Log.e("Error","Error al crear una incidencia")
            e.printStackTrace()
        }
    }

    suspend fun deleteIncident(authorization: String, id: Int) {
        try {
            userService.api.deleteIncident(authorization,id)
        }catch (e : Exception){
            Log.e("Error","Error al borrar una incidencia")
            e.printStackTrace()
        }
    }
}