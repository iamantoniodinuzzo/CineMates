package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.UserEntity
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import com.indisparte.database.entity.relations.UserFavGenreCrossRef
import com.indisparte.database.entity.relations.UserFavMediaCrossRef
import com.indisparte.database.entity.relations.UserWithFavActors
import com.indisparte.database.entity.relations.UserWithFavGenres
import com.indisparte.database.entity.relations.UserWithFavMedias
import com.indisparte.database.entity.relations.UserWithListAndMedia
import com.indisparte.database.entity.relations.UserWithLists

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface UserDao : BaseDao<UserEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavActorCrossRef(crossRef: UserFavActorCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavGenreCrossRef(crossRef: UserFavGenreCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavMediaCrossRef(crossRef: UserFavMediaCrossRef): Long

    @Delete
    fun deleteUserFavActorCrossRef(crossRef: UserFavActorCrossRef): Int

    @Delete
    fun deleteUserFavGenreCrossRef(crossRef: UserFavGenreCrossRef): Int

    @Delete
    fun deleteUserFavMediaCrossRef(crossRef: UserFavMediaCrossRef): Int

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserFavMedias(userId: Int): List<UserWithFavMedias>

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserFavGenres(userId: Int): List<UserWithFavGenres>

    @Query("SELECT * FROM UserFavGenreCrossRef WHERE userId=:userId AND genreId=:genreId ")
    fun getUserFavGenre(userId: Int, genreId:Int):UserFavGenreCrossRef?

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserFavActors(userId: Int): List<UserWithFavActors>

    @Query("SELECT * FROM UserFavActorCrossRef WHERE userId=:userId AND actorId=:actorId")
    fun getUserFavActor(userId:Int, actorId:Int):UserFavActorCrossRef?

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserLists(userId: Int): List<UserWithLists>

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserListsWithMedias(userId: Int): List<UserWithListAndMedia>


}