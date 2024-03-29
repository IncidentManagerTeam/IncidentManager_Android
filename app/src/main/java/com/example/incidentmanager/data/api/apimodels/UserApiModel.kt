package com.example.incidentmanager.data.api.apimodels

import android.os.Parcelable
import com.example.incidentmanager.data.db.repositories.models.User
import kotlinx.parcelize.Parcelize

data class UserApiModel (
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val course: String,
    var password: String
) {
    fun toUser(): User {
        return  User(
            email,
            name,
            surname,
            course,
            password
        )
    }
}

data class UserModel (
    val name: String,
    val surname: String,
    val email: String,
    val course: String,
    val password: String
)
data class UserLogin(
    val email:String,
    val password:String
)