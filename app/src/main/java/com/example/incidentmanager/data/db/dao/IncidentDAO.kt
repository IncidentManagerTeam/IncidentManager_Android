package com.example.incidentmanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.incidentmanager.data.db.entities.IncidenciaEntity
import com.example.incidentmanager.data.db.entities.ParkingEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncidentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createListIncident(listIncidentEntity:List<IncidenciaEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOneIncident(incidentEntity: IncidenciaEntity)
    @Query("SELECT * FROM incidencia")
    fun getAll(): Flow<List<IncidenciaEntity>>
    @Query("SELECT * FROM incidencia WHERE id = :incId ")
    fun getOneIncident(incId:Int):IncidenciaEntity
    @Query("DELETE FROM incidencia WHERE id = :incId")
    fun deleteOneIncident(incId:Int)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun updateOneIncident(incidentEntity: IncidenciaEntity)

}