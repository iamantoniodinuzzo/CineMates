package com.indisparte.genre.source.remote

import com.indisparte.base.MediaType
import com.indisparte.common.Genre
import com.indisparte.genre.mapper.mapToGenre
import com.indisparte.network.response.getListFromResponse
import com.indisparte.network.util.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
class GenreRemoteDataSource
@Inject
constructor(
    private val genreDataSource: GenreDataSource,
    private val queryMap: Map<String, String>,
) {

    fun getMovieGenreList(): Flow<Result<List<Genre>>> =
        getListFromResponse(
            request = { genreDataSource.getMovieGenreList(queryMap) },
            mapper = { response -> response.genres.map { it.mapToGenre(MediaType.MOVIE) } }
        )

    fun getTvGenreList(): Flow<Result<List<Genre>>> =
        getListFromResponse(
            request = { genreDataSource.getTvGenreList(queryMap) },
            mapper = { response -> response.genres.map { it.mapToGenre(mediaType = MediaType.MOVIE) } }
        )
}