package com.example.cinemates.model.local.dao

import androidx.room.*
import com.example.cinemates.model.data.Person
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 07:36
 */
@Dao
interface PersonDao {
    @Query("SELECT * FROM person")
    fun getPersons(): Flow<List<Person>>

    @Query("SELECT EXISTS(SELECT * FROM person WHERE id = :id)")
    fun isPersonFavorite(id : Int) : Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(person: Person)
}