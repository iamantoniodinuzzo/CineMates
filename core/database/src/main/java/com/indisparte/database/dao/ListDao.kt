package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.indisparte.database.entity.ListEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface ListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: ListEntity): Long

    // Recupero di una lista
    @Query("SELECT * FROM list WHERE id = :listId")
    fun getList(listId: Int): ListEntity?


    // Recupero di tutte le liste
    @Query("SELECT * FROM list")
    fun getAllLists(): List<ListEntity>

    @Delete
    fun deleteList(listEntity: ListEntity): Int
}