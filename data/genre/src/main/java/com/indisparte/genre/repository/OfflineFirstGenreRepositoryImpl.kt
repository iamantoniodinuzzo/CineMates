package com.indisparte.genre.repository

import com.indisparte.common.Genre
import com.indisparte.common.MediaType
import com.indisparte.genre.source.local.GenreLocalDataSource
import com.indisparte.genre.source.remote.GenreRemoteDataSource
import com.indisparte.network.Result
import com.indisparte.network.fetchFromLocalOrRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import timber.log.Timber
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
    private val LOG = Timber.tag(OfflineFirstGenreRepositoryImpl::class.java.simpleName)

    override fun getMovieGenreList(): Flow<Result<List<Genre>>> {
        return fetchFromLocalOrRemote(
            localFetch = { genreLocalDataSource.getAllMovieGenres().firstOrNull() },
            remoteFetch = { genreRemoteDataSource.getMovieGenreList() },
            saveToLocal = { genres -> genreLocalDataSource.insertGenres(genres, MediaType.MOVIE) }
        )
    }


    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        return fetchFromLocalOrRemote(
            localFetch = { genreLocalDataSource.getAllTvGenres().firstOrNull() },
            remoteFetch = { genreRemoteDataSource.getTvGenreList() },
            saveToLocal = { genres -> genreLocalDataSource.insertGenres(genres, MediaType.TV) }
        )
    }

    override fun getAllGenres(): Flow<List<Genre>> = genreLocalDataSource.getAllSavedGenres()

    override suspend fun fetchAllGenres() {// TODO: Can use the fetchFromLocalOrRemote func? 
        val localMovieGenres = genreLocalDataSource.getAllMovieGenres().firstOrNull()
        val localTvGenres = genreLocalDataSource.getAllTvGenres().firstOrNull()

        if (localMovieGenres.isNullOrEmpty() || localTvGenres.isNullOrEmpty()) {
            LOG.d("Retrieve remote genres 'cos one or both locals are null")
            val remoteMovieGenresFlow = genreRemoteDataSource.getMovieGenreList()
            val remoteTvGenresFlow = genreRemoteDataSource.getTvGenreList()

            combine(
                remoteMovieGenresFlow,
                remoteTvGenresFlow
            ) { (movieGenresResult, tvGenresResult) ->
                listOf(movieGenresResult, tvGenresResult)
            }.collectLatest { combinedGenresFlow ->
                if (combinedGenresFlow.all { it is Result.Success }) {
                    LOG.d("All genre results are success")

                    val movieGenres = (combinedGenresFlow[0] as Result.Success).data
                    val tvGenres = (combinedGenresFlow[1] as Result.Success).data
                    val commonGenres = movieGenres.intersect(tvGenres.toSet()).toList()

                    //remove all common genres
                    movieGenres.toMutableList().removeAll(commonGenres)
                    tvGenres.toMutableList().removeAll(commonGenres)

                    LOG.d("Insert all genres locally...")

                    genreLocalDataSource.insertGenres(movieGenres, MediaType.MOVIE).firstOrNull()
                    genreLocalDataSource.insertGenres(tvGenres, MediaType.TV).firstOrNull()
                    genreLocalDataSource.insertGenres(commonGenres, MediaType.BOTH).firstOrNull()
                    LOG.d("All genres inserted locally")

                }
            }
        }
        LOG.d("Retrieve LOCAL genres")

    }


    override fun updateSavedGenre(genre: Genre): Flow<Int> = flow {
        val result = genreLocalDataSource.updateGenre(genre).first()
        emit(result)
    }

    override fun getMyFavGenres(): Flow<List<Genre>> = flow {
        val result = genreLocalDataSource.getAllMyFavGenres().first()
        emit(result)
    }


    override fun getGenresByIds(genresId: List<Int>): Flow<List<Genre>> =
        genreLocalDataSource.getAllGenresById(genresId)
}