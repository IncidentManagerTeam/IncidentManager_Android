package com.example.incidentmanager.data.api.services

import com.example.incidentmanager.data.api.apimodels.CsrfApiModel
import com.example.incidentmanager.data.api.apimodels.IncidentApiModel
import com.example.incidentmanager.data.api.apimodels.ParkingApiModel
import com.example.incidentmanager.data.api.apimodels.UserApiModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.api.apimodels.UserModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton


interface UserApi {
    @GET("/api/users")
    suspend fun getAllUser(@Header("Authorization")authorization:String): List<UserApiModel>
    @GET("/api/users/{id}")
    suspend fun getOneUser(@Header("Authorization")authorization:String,@Path("id") id:Int):UserApiModel?
    @POST("/register")
    suspend fun register(@Body userModel: UserModel, @Header("X-CSRF-TOKEN") csrf: String): UserApiModel
    @POST("/login")
    suspend fun logIn(@Body userModel: UserLogin,@Header("X-CSRF-TOKEN") csrf: String):UserApiModel
}

@Singleton
class UserService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.66:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: UserApi = retrofit.create(UserApi::class.java)
}

interface IncidentApi {
    @GET("/api/incidents")
    suspend fun getAllIncidents(@Header("Authorization")authorization:String): List<IncidentApiModel>
    @GET("/api/incidents/{id}")
    suspend fun getOneIncident(@Header("Authorization")authorization:String,@Path("id") id:Int):IncidentApiModel
}

@Singleton
class IncidentService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.66:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: IncidentApi = retrofit.create(IncidentApi::class.java)
}

interface ParkingApi {
    @GET("/api/parkings")
    suspend fun getAllParkings(@Header("Authorization")authorization:String): List<ParkingApiModel>
    @GET("/api/parkings/{id}")
    suspend fun getOneParking(@Header("Authorization")authorization:String,@Path("id") id:Int):ParkingApiModel
}

@Singleton
class ParkingService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.66:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: ParkingApi = retrofit.create(ParkingApi::class.java)
}

interface CsrfApi {
    @GET("/csrf")
    suspend fun getCsrf(): CsrfApiModel
}

@Singleton
class CsrfService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.66:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val api: CsrfApi = retrofit.create(CsrfApi::class.java)
}
