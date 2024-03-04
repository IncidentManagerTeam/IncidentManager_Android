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
import com.example.incidentmanager.data.api.apimodels.UserModel
import com.example.incidentmanager.data.api.services.RegisterService

import javax.inject.Inject

class IncidentManagerApiRepository @Inject constructor(
    private val userService: UserService,
    private val incidentService: IncidentService,
    private val parkingService: ParkingService,
    private val csrfService: CsrfService,
    private val registerService: RegisterService
) {
    suspend fun getAllUser(): List<UserApiModel> {
        try {
            val userList = userService.api.getAllUser()
            return userList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de usuarios", e)
            return emptyList()
        }
    }

    suspend fun getOneUser(email:String): UserApiModel? {
        try {
            // Llamada a la API para obtener la lista de usuarios
            val userList = userService.api.getAllUser()
            for (user in userList){
                if (email == user.email) {
                    return user;
                }
            }
            return null
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de usuarios", e)
            return null
        }
    }

    suspend fun postRegister(user: UserModel, csrf: String): UserApiModel? {
        try {
            // Llamada a la API para registrar un nuevo usuario
            val newUser = registerService.api.postRegister(user, csrf)
            return newUser
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al registrar un nuevo usuario", e)
            return null // Devolvemos null en caso de error
        }
    }

    suspend fun getAllIncident(): List<IncidentApiModel> {
        try {
            // Llamada a la API para obtener la lista de indicencias
            val incidentList = incidentService.api.getAllIncidents()
            return incidentList
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener la lista de incidencias", e)
            return emptyList() // Devolvemos una lista vacía en caso de error
        }
    }

    suspend fun getAllParking(): List<ParkingApiModel> {
        try {
            // Llamada a la API para obtener la lista de parkings
            val parkingList = parkingService.api.getAllParkings()
            return parkingList
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener la lista de parkings", e)
            return emptyList() // Devolvemos una lista vacía en caso de error
        }
    }

    suspend fun getCsrf(): CsrfApiModel? {
        try {
            // Llamada a la API para obtener el csrf
            val csrfObject = csrfService.api.getCsrf()
            return csrfObject
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener el csrf", e)
            return null
        }
    }
}