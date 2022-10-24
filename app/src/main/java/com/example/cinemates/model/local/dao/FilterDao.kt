package com.example.cinemates.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cinemates.model.data.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Query("SELECT * FROM Filter")
    fun getFilters(): Flow<List<Filter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filter: Filter)

    @Delete
    suspend fun delete(filter: Filter)
}