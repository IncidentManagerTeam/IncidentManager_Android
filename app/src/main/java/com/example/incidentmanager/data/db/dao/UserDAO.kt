package com.example.incidentmanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.incidentmanager.data.db.entities.IncidenciaEntity
import com.example.incidentmanager.data.db.entities.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserDAO {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun createUser(userEntity: UserEntity)
        @Query("SELECT * FROM user WHERE id = :usId ")
        fun getUser(usId:Int): UserEntity
        @Query("DELETE FROM user WHERE id = :usId")
        fun deleteUser(usId:Int)
        @Update(onConflict = OnConflictStrategy.REPLACE)
        suspend  fun updateUser(userEntity: UserEntity)
}