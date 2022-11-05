package com.example.cinemates.model.local.dao

import androidx.room.*
import com.example.cinemates.model.entities.Filter
import kotlinx.coroutines.flow.Flow

@Dao
interface FilterDao : BaseDao<Filter> {

    @Query("SELECT * FROM filter")
    override fun getAll(): Flow<List<Filter>>
}