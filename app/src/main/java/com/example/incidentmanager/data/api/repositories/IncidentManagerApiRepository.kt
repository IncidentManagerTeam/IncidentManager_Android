package com.example.incidentmanager.data.api.repositories

import com.example.incidentmanager.data.api.apimodels.UserApiModel
import com.example.incidentmanager.data.api.services.UserService
import com.example.incidentmanager.data.api.services.IncidentService
import com.example.incidentmanager.data.api.services.ParkingService
import com.example.incidentmanager.data.api.services.CsrfService
import android.util.Log
import com.example.incidentmanager.data.api.apimodels.CsrfApiModel
import com.example.incidentmanager.data.api.apimodels.IncidentApiModel
import com.example.incidentmanager.data.api.apimodels.ParkingApiModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.api.apimodels.UserModel

import javax.inject.Inject

class IncidentManagerApiRepository @Inject constructor(
    private val userService: UserService,
    private val incidentService: IncidentService,
    private val parkingService: ParkingService,
    private val csrfService: CsrfService
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

    suspend fun postRegister(user: UserModel, csrf: String): UserApiModel? {
        try {
            val newUser = userService.api.register(user, csrf)
            return newUser
        } catch (e: Exception) {
            Log.e("Error", "Error al registrar un nuevo usuario", e)
            return null
        }
    }
    suspend fun login(userLogin: UserLogin):UserApiModel?{
        try {
            var token = csrfService.api.getCsrf()
            val user = userService.api.logIn(userLogin,"$token")
            return user
        }catch ( e : Exception ){
            var excepcion = e;
            Log.e("Error", "Error al hacer el login con la excepcion : $e")
            return null
        }
    }
    suspend fun getAllIncident(authorization:String): List<IncidentApiModel> {
        try {
            val incidentList = incidentService.api.getAllIncidents(authorization)
            return incidentList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de incidencias", e)
            return emptyList()
        }
    }

    suspend fun getAllParking(authorization:String): List<ParkingApiModel> {
        try {
            val parkingList = parkingService.api.getAllParkings(authorization)
            return parkingList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de parkings", e)
            return emptyList()
        }
    }

    suspend fun getCsrf(): CsrfApiModel? {
        try {
            val csrf = csrfService.api.getCsrf()
            return csrf
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener el csrf", e)
            return null
        }
    }
}