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
interface ListDao : BaseDao<ListEntity> {

    // Recupero di una lista
    @Query("SELECT * FROM list WHERE listId = :listId")
    fun getList(listId: Int): ListEntity?

    //Recupero le liste non associate ad un media
    @Transaction
    @Query("SELECT * FROM media where mediaId !=:mediaId")
    fun getListsNotContainingMedia(mediaId: Int): List<MediaWithLists>


}