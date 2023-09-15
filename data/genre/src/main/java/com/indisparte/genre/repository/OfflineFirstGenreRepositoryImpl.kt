package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.genre.source.local.GenreLocalDataSource
import com.indisparte.genre.source.remote.GenreRemoteDataSource
import com.indisparte.network.Result
import com.indisparte.network.fetchFromLocalOrRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class OfflineFirstGenreRepositoryImpl
@Inject
constructor(
    private val genreLocalDataSource: GenreLocalDataSource,
    private val genreRemoteDataSource: GenreRemoteDataSource,
) : GenreRepository {

    override fun getMovieGenreList(): Flow<Result<List<Genre>>> {
        return fetchFromLocalOrRemote(
            localFetch = { genreLocalDataSource.getAllSavedGenres().firstOrNull() },
            remoteFetch = { genreRemoteDataSource.getMovieGenreList() },
            saveToLocal = { genres -> genreLocalDataSource.insertGenres(genres) }
        )
    }


    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        return fetchFromLocalOrRemote(
            localFetch = { genreLocalDataSource.getAllSavedGenres().firstOrNull() },
            remoteFetch = { genreRemoteDataSource.getTvGenreList() },
            saveToLocal = { genres -> genreLocalDataSource.insertGenres(genres) }
        )
    }

    override fun updateSavedGenre(genre: Genre): Flow<Int> = flow {
        val result = genreLocalDataSource.updateGenre(genre).first()
        emit(result)
    }


    override fun getGenresByIds(genresId: List<Int>): Flow<List<Genre>> =
        genreLocalDataSource.getAllGenresById(genresId)
}