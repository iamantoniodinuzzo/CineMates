package com.indisparte.genre.source.local

import com.indisparte.common.Genre
import com.indisparte.common.MediaType
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.model.asDomain
import com.indisparte.database.model.asEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class GenreLocalDataSource
@Inject
constructor(
    private val dao: GenreDao,
) {
    fun insertGenres(genres: List<Genre>, mediaType: MediaType) = flow<Nothing> {
        val mappedEntity = genres.map { it.asEntity() }
        val updatedMappedEntity = mappedEntity.map { it.copy(mediaType = mediaType.id) }
        dao.insertGenreList(updatedMappedEntity)
    }.flowOn(Dispatchers.IO)

    fun getAllSavedGenres() = flow {
        val entityData = dao.getAllGenres()
        val mappedData = entityData.map { it.asDomain() }
        emit(mappedData)
    }.flowOn(Dispatchers.IO)

    fun getAllMovieGenres() = flow {
        val entityData = dao.getAllGenresByMediaType(MediaType.MOVIE.id)
        val mappedData = entityData.map { it.asDomain() }
        emit(mappedData)
    }.flowOn(Dispatchers.IO)

    fun getAllTvGenres() = flow {
        val entityData = dao.getAllGenresByMediaType(MediaType.TV.id)
        val mappedData = entityData.map { it.asDomain() }
        emit(mappedData)
    }.flowOn(Dispatchers.IO)

    fun getAllMyFavGenres() = flow {
        val entityData = dao.getAllMyFavGenres()
        val mappedData = entityData.map { it.asDomain() }
        emit(mappedData)
    }.flowOn(Dispatchers.IO)

    fun updateGenre(genre: Genre) = flow {
        val mappedGenre = genre.asEntity()
        val mappedUpdated = mappedGenre.copy(mediaType = genre.mediaType?.id)
        val result = dao.updateGenre(mappedUpdated)
        Timber.tag("GenreLocalDataSource").d("Update $result row with $mappedGenre")
        emit(result)
    }.flowOn(Dispatchers.IO)

    fun getAllGenresById(genresIds: List<Int>): Flow<List<Genre>> = flow {
        val entityGenres = dao.getAllGenresById(genresIds)
        val mappedGenres = entityGenres.map { it.asDomain() }
        emit(mappedGenres)
    }.flowOn(Dispatchers.IO)


}