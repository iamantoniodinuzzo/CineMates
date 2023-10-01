package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.model.FavoriteMediaEntity
import com.indisparte.database.model.MediaEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedia(media: MediaEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMovie(favoriteMovie: FavoriteMediaEntity): Long

    @Transaction
    fun insertFavoriteMovie(media: MediaEntity): Boolean {
        val favoriteMedia = FavoriteMediaEntity(mediaId = media.id)
        val insertMediaResult = insertMedia(media)
        val insertFavResult = insertFavoriteMovie(favoriteMedia)
        return (insertFavResult != 0L && insertMediaResult != 0L)
    }

    @Query("SELECT EXISTS (SELECT * FROM favorite_media WHERE mediaId = :mediaId)")
    fun isMediaFavorite(mediaId: Int): Boolean

    @Query("SELECT * FROM media INNER JOIN favorite_media ON media.id = favorite_media.mediaId")
    fun getAllFavoriteMedia(): List<MediaEntity>

    @Delete
    fun deleteMedia(media: MediaEntity)


}