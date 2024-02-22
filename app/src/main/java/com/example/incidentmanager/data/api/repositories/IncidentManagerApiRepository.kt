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
import com.example.incidentmanager.data.api.apimodels.RegisterApiModel
import com.example.incidentmanager.data.api.services.RegisterService

import javax.inject.Inject

class IncidentManagerApiRepository @Inject constructor(
    private val userService: UserService,
    private val incidentService: IncidentService,
    private val parkingService: ParkingService,
    private val csrfService: CsrfService,
    private val registerServie: RegisterService
) {
    suspend fun getAllUser(email:String): List<UserApiModel> {
        try {
            val simpleList = userService.api.getAllUser()
            return simpleList
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de usuarios", e)
            return emptyList()
        }
    }
    //SUPUESTO GET ONE
    suspend fun getOneUser(email:String): UserApiModel? {
        try {
            val simpleList = userService.api.getAllUser()
            for (i in simpleList){
                if( email == i.email){
                    return i;
                }
            }
            return null;
        } catch (e: Exception) {
            Log.e("Error", "Error al obtener la lista de usuarios", e)
            return null
        }
    }




    suspend fun getAllRegister(): RegisterApiModel {
        try {
            // Llamada a la API para obtener la lista de registros
            val simpleList = registerServie.api.getRegister()
            return simpleList
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener la lista de registros", e)
            return emptyList() // Devolvemos una lista vacía en caso de error
        }
    }

    suspend fun getAllIncident(): List<IncidentApiModel> {
        try {
            // Llamada a la API para obtener la lista de indicencias
            val simpleList = incidentService.api.getAllIncidents()
            return simpleList
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener la lista de incidencias", e)
            return emptyList() // Devolvemos una lista vacía en caso de error
        }
    }

    suspend fun getAllParking(): List<ParkingApiModel> {
        try {
            // Llamada a la API para obtener la lista de parkings
            val simpleList = parkingService.api.getAllParkings()
            return simpleList
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener la lista de parkings", e)
            return emptyList() // Devolvemos una lista vacía en caso de error
        }
    }

    suspend fun getCsrf(): CsrfApiModel {
        try {
            // Llamada a la API para obtener el csrf
            val simpleList = csrfService.api.getCsrf()
            return simpleList
        } catch (e: Exception) {
            // Manejo de errores en caso de falla en la llamada a la API
            Log.e("Error", "Error al obtener el csrf", e)
            return emptyList()
        }
    }
}