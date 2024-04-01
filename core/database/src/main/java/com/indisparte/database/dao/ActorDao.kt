package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import java.util.Date

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface ActorDao : BaseDao<ActorEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavActor(userFavActorCrossRef: UserFavActorCrossRef): Long

    @Transaction
    @Query("DELETE FROM UserFavActorCrossRef WHERE actorId = :actorId AND userId = :userId")
    fun deleteUserFavActor(actorId: Int, userId: Int): Int


    @Query("SELECT * FROM actor WHERE actorId = :actorId LIMIT 1")
    fun getActor(actorId: Int): ActorEntity?
}