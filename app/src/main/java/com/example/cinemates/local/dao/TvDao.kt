package com.example.cinemates.local.dao

import androidx.room.*
import com.example.cinemates.model.TvShow
import com.example.cinemates.model.PersonalStatus
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface TvDao : BaseDao<TvShow> {

    @Query("SELECT * FROM tv")
    fun getAll(): Flow<List<TvShow>>

    @Query("SELECT * FROM tv WHERE id=:id")
    fun getById(id: Int): TvShow?


    @Query("SELECT * FROM tv WHERE personalStatus=:personalStatus")
    fun getTvShowsWithThisPersonalStatus(personalStatus: PersonalStatus):Flow<List<TvShow>>

    @Query("SELECT * FROM tv WHERE favorite=1")
    fun getFavoriteTvShows(): Flow<List<TvShow>>

    @Query("SELECT EXISTS(SELECT * FROM tv WHERE id = :id AND personalStatus = :personalStatus)")
    fun isThisPersonalStatus(id: Int, personalStatus: PersonalStatus): Boolean

    @Query("SELECT EXISTS(SELECT * FROM tv WHERE id = :id AND favorite = 1)")
    fun isFavorite(id: Int): Boolean



}