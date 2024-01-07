package com.indisparte.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.indisparte.database.dao.base.BaseDao
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.relations.GenreMediaCrossRef

/**
 *@author Antonio Di Nuzzo
 */
@Dao
interface GenreDao : BaseDao<GenreEntity> {

    @Query("SELECT * FROM genres")
    fun getAllGenres(): List<GenreEntity>

    /**
     * Retrieves a list of genres filtered by media type from the database.
     *
     * @param mediaTypeId The media type ID to filter by.
     * @return A list of [GenreEntity] objects matching the specified media type filter and [MediaType.BOTH]
     */
    @Query("SELECT * FROM genre WHERE mediaType IN (:mediaTypeId, 0)")
    fun getAllGenresByMediaType(mediaTypeId: Int): List<GenreEntity>

    @Query("SELECT * FROM genre WHERE id IN (:genreIds)")
    fun getAllGenresById(genreIds: List<Int>): List<GenreEntity>

    @Transaction
    @Query("SELECT * FROM genre INNER JOIN UserFavGenreCrossRef ON genre.id = UserFavGenreCrossRef.genreId WHERE UserFavGenreCrossRef.userId = :userId")
    fun getUserFavoriteGenres(userId: Int): List<GenreEntity>

    @Transaction
    fun insertGenresForMedia(mediaId: Int, genres: List<GenreEntity>) {

        // Creazione di una lista di GenreMediaCrossRef associati al mediaId
        val genreMediaCrossRefList = genres.map { GenreMediaCrossRef(mediaId, it.id) }

        // Inserimento nella tabella GenreMediaCrossRef
        insertGenresInMedia(genreMediaCrossRefList)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenresInMedia(genreMediaCrossRefList: List<GenreMediaCrossRef>): Long

    override fun delete(entity: GenreEntity): Int =
        throw UnsupportedOperationException("Can't delete a genre!")
}