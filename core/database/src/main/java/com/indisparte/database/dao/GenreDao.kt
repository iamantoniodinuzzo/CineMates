package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.indisparte.database.entity.GenreEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface GenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenreList(genreList: List<GenreEntity>)

    @Query("SELECT * FROM GenreEntity")
    suspend fun getAllGenreList(): List<GenreEntity>

    @Update
    suspend fun updateGenre(genre: GenreEntity)

}