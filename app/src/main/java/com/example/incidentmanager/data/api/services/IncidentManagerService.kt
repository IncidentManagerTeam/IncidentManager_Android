package com.example.incidentmanager.data.api.services

import com.example.incidentmanager.data.api.apimodels.CsrfApiModel
import com.example.incidentmanager.data.api.apimodels.IncidentApiData
import com.example.incidentmanager.data.api.apimodels.ParkingApiModel
import com.example.incidentmanager.data.api.apimodels.UserApiModel
import com.example.incidentmanager.data.api.apimodels.UserLogin
import com.example.incidentmanager.data.api.apimodels.UserModel
import com.example.incidentmanager.data.db.repositories.models.IncidenciaRequest
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton


interface UserApi {
    @GET("/api/users")
    suspend fun getAllUser(@Header("Authorization")authorization:String): List<UserApiModel>
    @GET("/api/users/{id}")
    suspend fun getOneUser(@Header("Authorization")authorization:String,@Path("id") id:Int):UserApiModel?
    @POST("/register")
    suspend fun register(@Body userModel: UserModel): UserApiModel
    @POST("/login")
    suspend fun logIn(@Body userModel: UserLogin):UserApiModel
    @GET("/csrf")
    suspend fun getCsrf(): CsrfApiModel
    @GET("/api/parkings")
    suspend fun getAllParkings(@Header("Authorization")authorization:String): ParkingApiModel
    @GET("/api/parkings/{id}")
    suspend fun getOneParking(@Header("Authorization")authorization:String,@Path("id") id:Int):ParkingApiModel
    @GET("/api/incident")
    suspend fun getAllIncidents(@Header("Authorization")authorization:String): List<IncidentApiData>
    @GET("/api/incidents/{id}")
    suspend fun getOneIncident(@Header("Authorization")authorization:String,@Path("id") id:Int):IncidentApiData

    @Multipart
    @POST("/api/incident")
    suspend fun postIncident(
        @Header("Authorization") authorization: String,
        @Part("incident") incident: IncidenciaRequest,
        @Part image: MultipartBody.Part
    ): IncidentApiData

    @DELETE("/api/incident/{id}")
    suspend fun deleteIncident(@Header("Authorization") authorization: String,@Path("id") id: Int)
}

@Singleton
class UserService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://2c50-83-60-147-66.ngrok-free.app")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: UserApi = retrofit.create(UserApi::class.java)
}