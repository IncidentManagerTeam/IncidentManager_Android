package com.example.incidentmanager.data.api.services

import com.example.incidentmanager.data.api.apimodels.CsrfApiModel
import com.example.incidentmanager.data.api.apimodels.IncidentApiModel
import com.example.incidentmanager.data.api.apimodels.ParkingApiModel
import com.example.incidentmanager.data.api.apimodels.UserApiModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton


interface UserApi {
    @GET("api/users")
    suspend fun getAllUser(): UserApiModel
}

@Singleton
class UserService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-100-26-239-75.compute-1.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: UserApi = retrofit.create(UserApi::class.java)
}

interface IncidentApi {
    @GET("api/incidents")
    suspend fun getAllIncidents(): IncidentApiModel
}

@Singleton
class IncidentService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-100-26-239-75.compute-1.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: IncidentApi = retrofit.create(IncidentApi::class.java)
}

interface ParkingApi {
    @GET("api/parkings")
    suspend fun getAllParkings(): ParkingApiModel
}

@Singleton
class ParkingService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-100-26-239-75.compute-1.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: ParkingApi = retrofit.create(ParkingApi::class.java)
}

interface CsrfApi {
    @GET("csrf")
    suspend fun getCsrf(): CsrfApiModel
}

@Singleton
class CsrfService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-100-26-239-75.compute-1.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: CsrfApi = retrofit.create(CsrfApi::class.java)
}

interface RegisterApi {
    @GET("register")
    suspend fun getRegister(): CsrfApiModel
}

@Singleton
class RegisterService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://ec2-100-26-239-75.compute-1.amazonaws.com:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: RegisterApi = retrofit.create(RegisterApi::class.java)
}