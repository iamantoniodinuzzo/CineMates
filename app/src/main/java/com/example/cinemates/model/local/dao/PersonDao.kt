package com.example.cinemates.model.local.dao

import androidx.room.*
import com.example.cinemates.model.entities.Person
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface PersonDao : BaseDao<Person> {
    @Query("SELECT * FROM person")
    fun getAll(): Flow<List<Person>>

    @Query("SELECT EXISTS(SELECT * FROM person WHERE id = :id)")
    fun isPersonFavorite(id: Int): Boolean

}