package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.relations.GenreWithMedias
import com.indisparte.database.entity.relations.MediaListCrossRef
import com.indisparte.database.entity.relations.MediaWithLists
import com.indisparte.database.entity.relations.UserFavMediaCrossRef

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface MediaDao : BaseDao<MediaEntity> {


    //MEDIA IN FAVORITE

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavMediaCrossRef(crossRef: UserFavMediaCrossRef): Long

    @Query("SELECT * FROM media WHERE mediaId=:id")
    fun getMedia(id: Int): MediaEntity?

    @Query("SELECT * FROM UserFavMediaCrossRef WHERE mediaId = :mediaId AND userId = :userId LIMIT 1")
    fun getUserFavMedia(mediaId: Int, userId: Int): UserFavMediaCrossRef?

    @Delete
    fun deleteUserFavMediaCrossRef(crossRef: UserFavMediaCrossRef): Int


    // MEDIA IN LIST

    @Transaction
    @Query("SELECT * FROM media where mediaId =:mediaId")
    fun getMediaWithLists(mediaId: Int): List<MediaWithLists>

    @Query("SELECT * FROM MediaListCrossRef WHERE mediaId = :mediaId AND listId = :listId LIMIT 1")
    fun getMediaInList(listId: Int, mediaId: Int): MediaListCrossRef?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMediaListCrossRef(crossRef: MediaListCrossRef): Long

    @Delete
    fun deleteMediaFromList(crossRef: MediaListCrossRef): Int


    @Transaction
    @Query("SELECT * FROM genre WHERE genreId= :genreId")
    fun getMediasByGenre(genreId: Int): List<GenreWithMedias>

}