package com.indisparte.database.dao.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update


/**
 *@author Antonio Di Nuzzo
 */

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: T):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<T>)

    @Update
    fun update(entity: T): Int

    @Delete
    fun delete(entity: T):Int
}