package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.indisparte.database.model.MediaEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface MediaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedia(media: MediaEntity)

    @Query("SELECT * FROM media WHERE isFavorite==1 AND mediaType==:mediaType")
    fun getAllMyFavMediaByMediaType(mediaType: Int): List<MediaEntity>

    @Query("SELECT * FROM media WHERE id= :id")
    fun getMediaFromId(id: Int): MediaEntity?

    @Update
    fun updateMedia(media: MediaEntity): Int

    @Delete
    fun deleteMedia(media: MediaEntity): Int
}