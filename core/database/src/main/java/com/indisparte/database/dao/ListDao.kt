package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.indisparte.database.model.ListEntity
import com.indisparte.database.model.ListItemEntity
import com.indisparte.database.model.MediaEntity

/**
 * todo: La classe contiene dei metodi che non svolgono il loro dovere
 *@author Antonio Di Nuzzo
 */
@Dao
interface ListDao {

    @Insert
    fun insertList(list: ListEntity): Long

    @Delete
    fun deleteList(listEntity: ListEntity): Int

    // Recupero di tutti i media di una lista
    @Transaction
    @Query("SELECT media.* FROM media INNER JOIN list_item ON media.id = list_item.mediaId WHERE list_item.listId = :listId")
    fun getMediaInList(listId: Int): List<MediaEntity>


    // Recupero di tutti i media di una lista specificando il media type
    @Transaction
    @Query("SELECT media.* FROM media INNER JOIN list_item ON media.id = list_item.mediaId WHERE list_item.listId = :listId AND media.mediaType = :mediaType")
    fun getMediaInListWithType(listId: Int, mediaType: Int): List<MediaEntity>


    // Recupero di una lista
    @Query("SELECT * FROM list WHERE id = :listId")
    fun getList(listId: Int): ListEntity

    // Recupero di tutte le liste
    @Query("SELECT * FROM list")
    fun getAllLists(): List<ListEntity>

    // Inserimento di un media nella lista
    @Insert
    fun insertListItem(listItem: ListItemEntity)

    @Query("SELECT * FROM media WHERE id = :mediaId")
    fun getMediaById(mediaId: Int): MediaEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedia(media: MediaEntity)

    @Transaction
    fun insertMediaInList(listId: Int, media: MediaEntity, position: Int) {
        val existingMedia = getMediaById(media.id)
        if (existingMedia == null) {
            // Il media non esiste, quindi inseriscilo nella tabella MediaEntity
            insertMedia(media)
        }

        // Ora puoi inserire il media nella lista
        val listItem = ListItemEntity(listId = listId, mediaId = media.id, position = position, updateDate = "")
        insertListItem(listItem)
    }

    // Eliminazione di un media da una lista
    @Transaction
    fun deleteMediaFromList(listId: Int, mediaId: Int) {
        // Verifica se il media è presente solo in questa lista
        val mediaInOtherLists = getListsForMedia(mediaId)

        if (mediaInOtherLists.size == 1) {
            // Il media è presente solo in questa lista, elimina dalla tabella media
            deleteMediaById(mediaId)
        }

        // Elimina il media dalla lista corrente
        deleteMediaFromCurrentList(listId, mediaId)
    }

    @Query("SELECT * FROM list_item WHERE mediaId = :mediaId")
    fun getListsForMedia(mediaId: Int): List<ListItemEntity>

    @Query("DELETE FROM media WHERE id = :mediaId")
    fun deleteMediaById(mediaId: Int)

    @Query("DELETE FROM list_item WHERE listId = :listId AND mediaId = :mediaId")
    fun deleteMediaFromCurrentList(listId: Int, mediaId: Int)

    // Riordinamento di un media in una lista
    @Update
    fun updateMediaPosition(listItem: ListItemEntity)

    // Aggiornamento del titolo e/o della descrizione di una lista
    @Update
    fun updateListTitleAndDescription(list: ListEntity)
}
