package com.example.incidentmanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.incidentmanager.data.db.entities.IncidenciaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IncidentDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createListIncident(listIncidentEntity:List<IncidenciaEntity>)
    @Query("SELECT * FROM incidencia")
    fun getAll(): Flow<List<IncidenciaEntity>>
    @Query("SELECT * FROM incidencia WHERE id = :incId ")
    fun getOneIncident(incId:Int):IncidenciaEntity
    @Query("DELETE FROM incidencia WHERE id = :incId")
    fun deleteOneIncident(incId:Int)
    @Query("UPDATE incidencia SET titulo = :titulo ,descripcion =:descripcion ,lugar = :lugar,img = :img WHERE id = :incId")
    fun updateOneIncident(incId:Int,titulo:String,descripcion:String,lugar:String,img:String):IncidenciaEntity

}