package com.indisparte.genre.repository

import com.indisparte.base.MediaType
import com.indisparte.common.Genre
import com.indisparte.genre.source.local.GenreLocalDataSource
import com.indisparte.genre.source.remote.GenreRemoteDataSource
import com.indisparte.network.fetchFromLocalOrRemote
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
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
            localFetch = { genreLocalDataSource.getAllMovieGenres() },
            remoteFetch = { genreRemoteDataSource.getMovieGenreList() },
            saveToLocal = { genres -> genreLocalDataSource.insertGenres(genres, MediaType.MOVIE) }
        )
    }


    override fun getTvGenreList(): Flow<Result<List<Genre>>> {
        return fetchFromLocalOrRemote(
            localFetch = { genreLocalDataSource.getAllTvGenres() },
            remoteFetch = { genreRemoteDataSource.getTvGenreList() },
            saveToLocal = { genres -> genreLocalDataSource.insertGenres(genres, MediaType.TV) }
        )
    }

    override fun getAllGenres(): List<Genre> {
        val result = runBlocking {
            genreLocalDataSource.getAllSavedGenres()
        }
        return result
    }

    override suspend fun fetchAllGenres() {// TODO: Can use the fetchFromLocalOrRemote func? 
        val localMovieGenres = genreLocalDataSource.getAllMovieGenres()
        val localTvGenres = genreLocalDataSource.getAllTvGenres()

        if (localMovieGenres.isEmpty() || localTvGenres.isEmpty()) {
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

                    genreLocalDataSource.insertGenres(movieGenres, MediaType.MOVIE)
                    genreLocalDataSource.insertGenres(tvGenres, MediaType.TV)
                    genreLocalDataSource.insertGenres(commonGenres, MediaType.MOVIE_TV)
                    LOG.d("All genres inserted locally")

                }
            }
        }
        LOG.d("Retrieve LOCAL genres")

    }


    override fun getMyFavGenres(): Flow<List<Genre>> = flow {
        val result = genreLocalDataSource.getAllUserFavGenres()
        emit(result)
    }


    override fun getGenresByIds(genresId: List<Int>): List<Genre> = runBlocking {
        genreLocalDataSource.getAllGenresById(genresId)

    }
}