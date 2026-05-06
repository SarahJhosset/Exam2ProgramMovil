package com.ucb.primerproyecto.dollar.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucb.primerproyecto.dollar.data.entity.DollarEntity

@Dao
interface DollarDao {
    @Query("SELECT * FROM dollars")
    suspend fun getList(): List<DollarEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dollar: DollarEntity)

    @Query("DELETE FROM dollars")
    suspend fun deleteAll()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDollars(lists: List<DollarEntity>)

    @Query("SELECT COUNT(*) FROM dollars")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(dollar: DollarEntity)

    // Borra los N registros más antiguos (menor timestamp = más antiguo)
    @Query("""
    DELETE FROM dollars 
    WHERE id IN (
        SELECT id FROM dollars 
        ORDER BY timestamp ASC 
        LIMIT :count
    )
""")
    suspend fun deleteOldest(count: Int)
}