package com.example.cinemates.model.local.dao

import androidx.room.*
import com.example.cinemates.model.data.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao {

    @Query("SELECT * FROM filter")
    fun getFilters(): Flow<List<Filter>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filter: Filter)

    @Delete
    suspend fun delete(filter: Filter)
}