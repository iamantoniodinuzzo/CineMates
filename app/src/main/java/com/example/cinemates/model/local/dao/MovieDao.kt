package com.example.cinemates.model.local.dao

import androidx.room.*
import com.example.cinemates.model.entities.Movie
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getMovie(id: Int): Movie?

    @Query("SELECT * FROM movie WHERE personalStatus=1")
    fun getToSeeMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE personalStatus=0")
    fun getSeenMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE favorite=1")
    fun getFavoriteMovies(): Flow<List<Movie>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND favorite = 1)")
    fun isFavorite(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND personalStatus = 1 )")
    fun isToSee(id: Int): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND personalStatus = 0)")
    fun isSeen(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)
}