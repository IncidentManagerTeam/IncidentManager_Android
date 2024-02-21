package com.example.incidentmanager.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidentmanager.data.db.repositories.models.Parking
import com.example.incidentmanager.data.db.repositories.models.User

@Entity(tableName="user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val nombre: String,
    val apellidos: String,
    val rol: String,
    val clase: String
)

fun List<UserEntity>.asListUser():List<User> {
    return this.map {
        User(
            it.id,
            it.email,
            it.nombre,
            it.apellidos,
            it.rol,
            it.clase
        )
    }
}