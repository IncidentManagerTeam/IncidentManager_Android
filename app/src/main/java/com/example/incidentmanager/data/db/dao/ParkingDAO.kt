package com.example.incidentmanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import com.example.incidentmanager.data.db.entities.ParkingEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ParkingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createListIncident(listIncidentEntity:List<ParkingEntity>)
    @Query("SELECT * FROM parking")
    fun getAll(): Flow<List<ParkingEntity>>
    @Query("SELECT * FROM parking WHERE nsolicitud = :pkgId ")
    fun getOneIncident(pkgId:Int): ParkingEntity
    @Query("DELETE FROM parking WHERE nsolicitud = :pkgId")
    fun deleteOneIncident(pkgId:Int)
    @Query("UPDATE parking SET matricula = :matricula ,nalumnos = :nalumnos,fecha = :fecha,estado = :estado WHERE nsolicitud = :pkgId")
    fun updateOneIncident(pkgId:Int,matricula:String,nalumnos:Int,fecha:String ,estado:String): ParkingEntity


}