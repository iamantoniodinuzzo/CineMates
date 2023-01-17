package com.example.cinemates.local.dao

import androidx.room.*
import com.example.cinemates.model.Movie
import com.example.cinemates.model.PersonalStatus
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface MovieDao : BaseDao<Movie> {

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE id=:id")
    fun getById(id: Int): Movie?


    @Query("SELECT * FROM movie WHERE personalStatus=:personalStatus")
    fun getMoviesWithThisPersonalStatus(personalStatus: PersonalStatus):Flow<List<Movie>>

    @Query("SELECT * FROM movie WHERE favorite=1")
    fun getFavoriteMovies(): Flow<List<Movie>>

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND personalStatus = :personalStatus)")
    fun isThisPersonalStatus(id: Int, personalStatus: PersonalStatus): Boolean

    @Query("SELECT EXISTS(SELECT * FROM movie WHERE id = :id AND favorite = 1)")
    fun isFavorite(id: Int): Boolean



}