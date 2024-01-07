package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import java.util.Date

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface ActorDao : BaseDao<ActorEntity> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavActor(userFavActorCrossRef: UserFavActorCrossRef): Long

    @Transaction
    @Query("SELECT * FROM actor INNER JOIN UserFavActorCrossRef ON actor.id = UserFavActorCrossRef.actorId WHERE UserFavActorCrossRef.userId = :userId")
    fun getFavoriteActors(userId: Int): List<ActorEntity>

    @Transaction
    @Query("DELETE FROM UserFavActorCrossRef WHERE actorId = :actorId AND userId = :userId")
    fun deleteUserFavActor(actorId: Int, userId: Int): Int

    @Transaction
    fun setActorAsFavorite(actor: ActorEntity, userId: Int): Boolean {
        // Verifica se l'attore esiste nella tabella Actor
        val existingActor = getActor(actor.id)

        // Se l'attore non esiste, inseriscilo nella tabella Actor
        if (existingActor == null) {
            insert(actor)
        }

        // Inserisce l'attore tra i preferiti dell'utente
        val userFavActorCrossRef = UserFavActorCrossRef(actorId = actor.id, userId = userId, favDate = Date())
        val insertionResult = insertUserFavActor(userFavActorCrossRef)
        return insertionResult > 0
    }

    @Query("SELECT * FROM actor WHERE id = :actorId LIMIT 1")
    fun getActor(actorId: Int): ActorEntity?
}