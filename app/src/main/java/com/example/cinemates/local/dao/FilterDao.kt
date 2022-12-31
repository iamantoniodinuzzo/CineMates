package com.example.cinemates.local.dao

import androidx.room.*
import com.example.cinemates.model.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao : BaseDao<Filter> {

    @Query("SELECT * FROM filter")
    fun getAll(): Flow<List<Filter>>
}