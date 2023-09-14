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

    @Query("SELECT * FROM genres WHERE id IN (:genreIds)")
    fun getAllGenresById(genreIds: List<Int>): List<GenreEntity>

    @Update
    fun updateGenre(genreEntity: GenreEntity):Int


}