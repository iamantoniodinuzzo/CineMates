package com.indisparte.genre.repository

import com.indisparte.genre.mapper.mapToGenre
import com.indisparte.genre.source.GenreDataSource
import com.indisparte.common.Genre
import com.indisparte.network.Result
import com.indisparte.network.getListFromResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class GenreRepositoryImpl
@Inject
constructor(
    private val genreDataSource: GenreDataSource,
    private val queryMap: Map<String, String>
) : GenreRepository {
    override  fun getMovieGenreList(): Flow<Result<List<Genre>>> =
        getListFromResponse(
            request = {genreDataSource.getMovieGenreList(queryMap)},
            mapper = {response->response.genres.map { it.mapToGenre() }}
        )

    override  fun getTvGenreList(): Flow<Result<List<Genre>>> =
        getListFromResponse(
            request = {genreDataSource.getTvGenreList(queryMap)},
            mapper = {response->response.genres.map { it.mapToGenre() }}
        )


}