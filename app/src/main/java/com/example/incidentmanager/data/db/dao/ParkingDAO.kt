package com.example.incidentmanager.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.example.incidentmanager.data.db.entities.ParkingEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface ParkingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createListParking(listIncidentEntity:List<ParkingEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOneParking(parkingEntity:ParkingEntity)
    @Query("SELECT * FROM parking")
    fun getAll(): Flow<List<ParkingEntity>>
    @Query("SELECT * FROM parking WHERE nsolicitud = :pkgId ")
    fun getOneParking(pkgId:Int): ParkingEntity
    @Query("DELETE FROM parking WHERE nsolicitud = :pkgId")
    fun deleteOneParking(pkgId:Int)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun updateOneParking(parkingEntity: ParkingEntity)


}