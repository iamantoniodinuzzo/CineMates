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
import com.indisparte.database.entity.relations.UserWithDefaultListAndMedia
import com.indisparte.database.entity.relations.UserWithDefaultLists
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

    //User FAV Actor
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavActorCrossRef(crossRef: UserFavActorCrossRef): Long

    @Delete
    fun deleteUserFavActorCrossRef(crossRef: UserFavActorCrossRef): Int

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserFavActors(userId: Int): List<UserWithFavActors>

    @Query("SELECT * FROM UserFavActorCrossRef WHERE userId=:userId AND actorId=:actorId")
    fun getUserFavActor(userId:Int, actorId:Int):UserFavActorCrossRef?



    //User FAV Genre
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavGenreCrossRef(crossRef: UserFavGenreCrossRef): Long

    @Delete
    fun deleteUserFavGenreCrossRef(crossRef: UserFavGenreCrossRef): Int

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserFavGenres(userId: Int): List<UserWithFavGenres>

    @Query("SELECT * FROM UserFavGenreCrossRef WHERE userId=:userId AND genreId=:genreId ")
    fun getUserFavGenre(userId: Int, genreId:Int):UserFavGenreCrossRef?



    //User FAV Media
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavMediaCrossRef(crossRef: UserFavMediaCrossRef): Long

    @Delete
    fun deleteUserFavMediaCrossRef(crossRef: UserFavMediaCrossRef): Int

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserFavMedias(userId: Int): List<UserWithFavMedias>


    //User Lists
    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserLists(userId: Int): List<UserWithLists>

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserListsWithMedias(userId: Int): List<UserWithListAndMedia>


    //User default Lists
    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserDefaultLists(userId: Int): List<UserWithDefaultLists>

    @Transaction
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUserDefaultListsWithMedias(userId: Int): List<UserWithDefaultListAndMedia>


}