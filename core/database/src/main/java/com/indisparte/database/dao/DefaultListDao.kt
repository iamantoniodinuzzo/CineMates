package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.DefaultListEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.relations.MediaWithDefaultLists
import com.indisparte.database.entity.relations.MediaWithLists

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface DefaultListDao : BaseDao<DefaultListEntity> {

    // Recupero di una lista
    @Query("SELECT * FROM defaultlist WHERE listId = :listId")
    fun getDefaultList(listId: Int): DefaultListEntity?


}