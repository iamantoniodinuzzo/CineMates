package com.example.cinemates.model.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg entity: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(entity: T)

    @Delete
    fun delete(entity: T)

    @Delete
    fun delete(vararg entity: T)




}
