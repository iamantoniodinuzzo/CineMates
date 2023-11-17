package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.indisparte.database.entity.FavoritePersonEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoritePerson(personEntity: FavoritePersonEntity): Long

    @Delete
    fun removeFavoritePerson(personEntity: FavoritePersonEntity): Int

    @Query("SELECT * FROM person ")
    fun getAllFavoritePerson(): List<FavoritePersonEntity>

    @Query("SELECT EXISTS (SELECT * FROM person WHERE id = :personId)")
    fun isPersonFavorite(personId: Int): Boolean

}