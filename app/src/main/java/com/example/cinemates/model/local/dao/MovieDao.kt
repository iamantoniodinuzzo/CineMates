package com.example.cinemates.model.local.dao

import androidx.room.*
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.PersonalStatus
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getMovies(): Flow<List<Movie>>


    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND favorite = 1)")
    fun isMovieFavorite(id : Int) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)
}