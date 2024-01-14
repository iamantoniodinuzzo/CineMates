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

    @Query("SELECT * FROM genre")
    fun getAllGenres(): List<GenreEntity>

    /**
     * Retrieves a list of genres filtered by media type from the database.
     *
     * @param mediaTypeId The media type ID to filter by.
     * @return A list of [GenreEntity] objects matching the specified media type filter and [MediaType.BOTH]
     */
    @Query("SELECT * FROM genre WHERE mediaType IN (:mediaTypeId, 0)")
    fun getAllGenresByMediaType(mediaTypeId: Int): List<GenreEntity>

    @Query("SELECT * FROM genre WHERE genreId IN (:genreIds)")
    fun getAllGenresById(genreIds: List<Int>): List<GenreEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenresInMedia(genreMediaCrossRefList: List<GenreMediaCrossRef>): LongArray

    override fun delete(entity: GenreEntity): Int =
        throw UnsupportedOperationException("Can't delete a genre!")
}