package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.FavoriteMediaEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.ListItemEntity
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.SeenMediaEntity
import com.indisparte.database.entity.ToSeeMediaEntity

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface MediaDao : BaseDao<MediaEntity> {

    @Query("DELETE FROM media WHERE id = :mediaId")
    fun deleteMediaById(mediaId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteMedia(favoriteMovie: FavoriteMediaEntity): Long

    @Query("DELETE FROM favorite_media WHERE mediaId = :mediaId")
    fun deleteFavoriteMedia(mediaId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeenMedia(favoriteMovie: SeenMediaEntity): Long

    @Query("DELETE FROM seen_media WHERE mediaId = :mediaId")
    fun deleteSeenMedia(mediaId: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToSeeMedia(favoriteMovie: ToSeeMediaEntity): Long

    @Query("DELETE FROM to_see_media WHERE mediaId = :mediaId")
    fun deleteToSeeMedia(mediaId: Int): Int

    @Transaction
    fun insertFavoriteMedia(media: MediaEntity): Boolean {
        val existingMedia = getMediaById(media.id)
        if (existingMedia == null) {
            //Il media non è stato mai memorizzato
            //memorizziamolo
            val mediaInsertionResult = insert(media)
            if (mediaInsertionResult == 0L) {
                //Se l'inserimento del media non ha avuto successo
                //Restituisci false terminando il metodo
                return false
            }
        }
        val favoriteMedia = FavoriteMediaEntity(mediaId = media.id)
        val insertFavResult = insertFavoriteMedia(favoriteMedia)

        return (insertFavResult != 0L)
    }

    // Eliminazione di un media dalla lista dei preferiti
    @Transaction
    fun deleteMediaFromFavorite(mediaId: Int): Boolean {
        // Elimina il media dai preferiti
        val result = deleteFavoriteMedia(mediaId)

        // Verifica se il media è presente in altre liste
        val mediaInOtherLists = isMediaSeen(mediaId) || isMediaToSee(mediaId) ||
                getListsForMedia(mediaId).isNotEmpty()

        if (!mediaInOtherLists) {
            // Il media è presente solo in questa lista, elimina dalla tabella media
            deleteMediaById(mediaId)
        }

        return result > 0L

    }

    @Transaction
    fun insertSeenMedia(media: MediaEntity): Boolean {
        val existingMedia = getMediaById(media.id)
        if (existingMedia == null) {
            //Il media non è stato mai memorizzato
            //Inseriamolo
            val mediaInsertionResult = insert(media)
            if (mediaInsertionResult == 0L) {
                //Se l'inserimento del media non ha avuto successo
                //Restituisci false
                return false
            }
        }
        val seenMedia = SeenMediaEntity(mediaId = media.id)
        val insertSeenResult = insertSeenMedia(seenMedia)

        //Controllo se il media è anche seen
        val isToSee = isMediaToSee(media.id)
        if (isToSee) {
            //In caso positivo devo rimuoverlo per logica,
            // un media visto non può rimanere nella lista dei media  da vedere
            val isRemoved = deleteMediaFromToSee(media.id)
            if (!isRemoved)
                return false
        }
        return (insertSeenResult != 0L)
    }

    // Eliminazione di un media dalla lista dei visti
    @Transaction
    fun deleteMediaFromSeen(mediaId: Int): Boolean {
        // Elimina il media dai preferiti
        val result = deleteSeenMedia(mediaId)

        // Verifica se il media è presente in altre liste
        val mediaInOtherLists = isMediaSeen(mediaId) || isMediaToSee(mediaId) ||
                getListsForMedia(mediaId).isNotEmpty()

        if (!mediaInOtherLists) {
            // Il media è presente solo in questa lista, elimina dalla tabella media
            deleteMediaById(mediaId)
        }

        return result > 0L

    }

    @Transaction
    fun insertToSeeMedia(media: MediaEntity): Boolean {
        val existingMedia = getMediaById(media.id)
        if (existingMedia == null) {
            //Il media non è stato mai memorizzato
            //Inseriamolo
            val mediaInsertionResult = insert(media)
            if (mediaInsertionResult == 0L) {
                //Se l'inserimento del media non ha avuto successo
                //Restituisci false
                return false
            }
        }
        val toSeeMedia = ToSeeMediaEntity(mediaId = media.id)
        val insertToSeeResult = insertToSeeMedia(toSeeMedia)

        //Controllo se il media è anche seen
        val isSeen = isMediaSeen(media.id)
        if (isSeen) {
            //In caso positivo devo rimuoverlo per logica,
            // un media da vedere non può rimanere nella lista dei visti.
            val isRemoved = deleteMediaFromSeen(media.id)
            if (!isRemoved)
                return false
        }
        return (insertToSeeResult != 0L)
    }

    // Eliminazione di un media dalla lista da vedere
    @Transaction
    fun deleteMediaFromToSee(mediaId: Int): Boolean {
        // Elimina il media dai preferiti
        val result = deleteToSeeMedia(mediaId)

        // Verifica se il media è presente in altre liste
        val mediaInOtherLists = isMediaSeen(mediaId) || isMediaToSee(mediaId) ||
                getListsForMedia(mediaId).isNotEmpty()

        if (!mediaInOtherLists) {
            // Il media è presente solo in questa lista, elimina dalla tabella media
            deleteMediaById(mediaId)
        }

        return result > 0L
    }

    @Transaction
    fun insertMediaInList(listId: Int, media: MediaEntity, position: Int): Boolean {
        val existingMedia = getMediaById(media.id)
        if (existingMedia == null) {
            // Il media non esiste, quindi inseriscilo nella tabella MediaEntity

            val insertionResult = insert(media)
            if (insertionResult == 0L)
                return false
        }

        // Ora puoi inserire il media nella lista
        val listItem = ListItemEntity(
            listId = listId,
            mediaId = media.id,
            position = position,
            updateDate = ""
        )
        val insertionResult = insertListItem(listItem)

        return insertionResult > 0L
    }

    // Eliminazione di un media da una lista
    @Transaction
    fun deleteMediaFromList(listId: Int, mediaId: Int) {
        // Elimina il media dalla lista corrente
        deleteMediaFromCurrentList(listId, mediaId)

        // Verifica se il media è presente solo in questa lista
        val mediaInOtherLists = getListsForMedia(mediaId)

        if (mediaInOtherLists.size == 1) {
            // Il media è presente solo in questa lista, elimina dalla tabella media
            deleteMediaById(mediaId)
        }

    }

    @Query("DELETE FROM list_item WHERE listId = :listId AND mediaId = :mediaId")
    fun deleteMediaFromCurrentList(listId: Int, mediaId: Int)

    @Query("SELECT * FROM list_item WHERE mediaId = :mediaId")
    fun getListsForMedia(mediaId: Int): List<ListItemEntity>


    @Transaction
    @Query("SELECT EXISTS (SELECT * FROM favorite_media WHERE mediaId = :mediaId)")
    fun isMediaFavorite(mediaId: Int): Boolean

    @Transaction
    @Query("SELECT EXISTS (SELECT * FROM seen_media WHERE mediaId = :mediaId)")
    fun isMediaSeen(mediaId: Int): Boolean

    @Transaction
    @Query("SELECT EXISTS (SELECT * FROM to_see_media WHERE mediaId = :mediaId)")
    fun isMediaToSee(mediaId: Int): Boolean

    @Transaction
    @Query("SELECT media.id, media.mediaName, media.popularity, media.posterPath,media.voteAverage, media.mediaType  FROM media INNER JOIN favorite_media ON media.id = favorite_media.mediaId AND mediaType = :mediaType")
    fun getAllFavoriteMediaByMediaType(mediaType: Int): List<MediaEntity>

    @Transaction
    @Query("SELECT media.id, media.mediaName, media.popularity, media.posterPath,media.voteAverage, media.mediaType  FROM media INNER JOIN seen_media ON media.id = seen_media.mediaId AND mediaType = :mediaType")
    fun getSeenMediaByMediaType(mediaType: Int): List<MediaEntity>

    @Transaction
    @Query("SELECT media.id, media.mediaName, media.popularity, media.posterPath,media.voteAverage, media.mediaType  FROM media INNER JOIN to_see_media ON media.id = to_see_media.mediaId AND mediaType = :mediaType")
    fun getToSeeMediaByMediaType(mediaType: Int): List<MediaEntity>

    // Recupero di tutti i media di una lista
    @Transaction
    @Query("SELECT media.* FROM media INNER JOIN list_item ON media.id = list_item.mediaId WHERE list_item.listId = :listId")
    fun getMediaInList(listId: Int): List<MediaEntity>


    // Recupero di tutti i media di una lista specificando il media type
    @Transaction
    @Query("SELECT media.* FROM media INNER JOIN list_item ON media.id = list_item.mediaId WHERE list_item.listId = :listId AND media.mediaType = :mediaType")
    fun getMediaInListWithType(listId: Int, mediaType: Int): List<MediaEntity>


    // Inserimento di un media nella lista
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertListItem(listItem: ListItemEntity): Long

    @Query("SELECT * FROM media WHERE id = :mediaId")
    fun getMediaById(mediaId: Int): MediaEntity?

    // Riordinamento di un media in una lista
    @Update
    fun updateMediaPosition(listItem: ListItemEntity)

    // Aggiornamento del titolo e/o della descrizione di una lista
    @Update
    fun updateListTitleAndDescription(list: ListEntity)

}