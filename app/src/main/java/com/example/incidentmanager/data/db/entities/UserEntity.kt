package com.example.incidentmanager.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.incidentmanager.data.db.repositories.models.Parking
import com.example.incidentmanager.data.db.repositories.models.User

@Entity(tableName="user")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val username: String,
    val nombre: String,
    val apellidos: String,
    val rol: String,
    val clase: String
)

fun List<UserEntity>.asListUser():List<User> {
    return this.map {
        User(
            it.id,
            it.username,
            it.nombre,
            it.apellidos,
            it.rol,
            it.clase
        )
    }
}