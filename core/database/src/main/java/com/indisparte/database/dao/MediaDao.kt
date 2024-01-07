package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.relations.MediaListCrossRef
import com.indisparte.database.entity.relations.UserFavMediaCrossRef
import java.util.Date

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface MediaDao : BaseDao<MediaEntity> {


    //MEDIA IN FAVORITE

    @Transaction
    fun insertMediaToFavorites(media: MediaEntity, userId: Int): Boolean {
        val favDate = Date()

        //Verifica se il media è mai stato memorizzato
        val existingMedia = getMedia(media.id)
        if (existingMedia == null) {
            //Il media non è mai stato inserito
            val insertionResult = insert(media)
            //Se l'inserimento non è andato a buon fine, ritorna false
            if (insertionResult <= 0)
                return false
        }

        // Verifica se il media è già nei preferiti per questo utente
        val existingFavMedia = getUserFavMedia(media.id, userId)
        if (existingFavMedia == null) {
            // Se il media non è ancora nei preferiti, inserisci il nuovo record
            val newFavMedia = UserFavMediaCrossRef(media.id, userId, favDate)
            val insertionResult = insertUserFavMedia(newFavMedia)
            //Se l'inserimento non è andato a buon fine, ritorna false
            if (insertionResult <= 0)
                return false
        }

        return true
    }

    @Query("SELECT * FROM media WHERE id=:id")
    fun getMedia(id: Int): MediaEntity?

    @Query("SELECT * FROM UserFavMediaCrossRef WHERE mediaId = :mediaId AND userId = :userId LIMIT 1")
    fun getUserFavMedia(mediaId: Int, userId: Int): UserFavMediaCrossRef?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserFavMedia(userFavMediaCrossRef: UserFavMediaCrossRef): Long


    @Transaction
    @Query("SELECT * FROM media WHERE id IN (SELECT mediaId FROM UserFavMediaCrossRef WHERE userId = :userId)")
    fun getFavoriteMedia(userId: Int): List<MediaEntity>

    @Transaction
    @Query("DELETE FROM UserFavMediaCrossRef WHERE mediaId = :mediaId AND userId = :userId")
    fun deleteMediaFromFavorites(mediaId: Int, userId: Int): Int


    // MEDIA IN LIST

    @Transaction
    fun insertMediaToList(media: MediaEntity, listId: Int, position: Int): Boolean {
        val insertionDate = Date()

        // Verifica se il media è già stato inserito nel database e la lista esistono
        val existingMedia = getMedia(media.id)
        if (existingMedia == null) {
            //Il media non è mai stato inserito nel database
            val insertionResult = insert(media)
            if (insertionResult <= 0)
                return false
        }

        // Crea un nuovo record in MediaListCrossRef
        val mediaListCrossRef = MediaListCrossRef(media.id, listId, insertionDate, position)
        val insertionResult = insertMediaInList(mediaListCrossRef)

        return insertionResult > 0
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMediaInList(mediaListCrossRef: MediaListCrossRef): Long

    @Transaction
    @Query("DELETE FROM MediaListCrossRef WHERE mediaId = :mediaId AND listId = :listId")
    fun deleteMediaFromList(mediaId: Int, listId: Int): Int

    @Transaction
    @Query("SELECT * FROM Media WHERE id IN (SELECT mediaId FROM MediaListCrossRef WHERE listId = :listId)")
    fun getMediaInList(listId: Int): List<MediaEntity>

    @Transaction
    @Query("SELECT * FROM Media WHERE id IN (SELECT mediaId FROM GenreMediaCrossRef WHERE genreId IN (:genreIds))")
    fun getMediaByGenres(genreIds: List<Int>): List<MediaEntity>


}