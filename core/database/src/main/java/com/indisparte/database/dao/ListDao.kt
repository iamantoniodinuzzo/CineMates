package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.ListEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface ListDao : BaseDao<ListEntity> {

    // Recupero di una lista
    @Query("SELECT * FROM list WHERE id = :listId")
    fun getList(listId: Int): ListEntity?

    @Query("SELECT * FROM list WHERE ownerId=:userId")
    fun getAllListsByUserId(userId: Int): List<ListEntity>

    @Transaction
    @Query("SELECT * FROM list INNER JOIN MediaListCrossRef ON list.id = MediaListCrossRef.listId WHERE MediaListCrossRef.mediaId = :mediaId")
    fun getListsContainingMedia(mediaId: Int): List<ListEntity>

    @Transaction
    @Query("SELECT * FROM list WHERE NOT EXISTS (SELECT 1 FROM MediaListCrossRef WHERE list.id = MediaListCrossRef.listId AND MediaListCrossRef.mediaId = :mediaId)")
    fun getListsNotContainingMedia(mediaId: Int): List<ListEntity>

}