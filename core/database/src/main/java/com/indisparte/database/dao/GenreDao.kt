package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.indisparte.database.model.GenreEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenreList(genres: List<GenreEntity>)

    @Query("SELECT * FROM genres")
    fun getAllGenres(): List<GenreEntity>

    /**
     * Retrieves a list of genres filtered by media type from the database.
     *
     * @param mediaTypeId The media type ID to filter by.
     * @return A list of [GenreEntity] objects matching the specified media type filter and [MediaType.BOTH]
     */
    @Query("SELECT * FROM genres WHERE mediaType IN (:mediaTypeId, 0)")
    fun getAllGenresByMediaType(mediaTypeId: Int): List<GenreEntity>

    @Query("SELECT * FROM genres WHERE isFavorite==1")
    fun getAllMyFavGenres(): List<GenreEntity>

    @Query("SELECT * FROM genres WHERE id IN (:genreIds)")
    fun getAllGenresById(genreIds: List<Int>): List<GenreEntity>

    @Update
    fun updateGenre(genreEntity: GenreEntity): Int


}