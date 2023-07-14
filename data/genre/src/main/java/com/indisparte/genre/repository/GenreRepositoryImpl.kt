package com.indisparte.genre.repository

import com.indisparte.genre.mapper.mapToGenre
import com.indisparte.genre.source.GenreDataSource
import com.indisparte.model.entity.Genre
import com.indisparte.network.Resource
import com.indisparte.network.getListFromResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.QueryMap
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GenreRepositoryImpl
@Inject
constructor(
    private val genreDataSource: GenreDataSource,
    private val queryMap: Map<String, String>
) : GenreRepository {
    override suspend fun getMovieGenreList(): Flow<Resource<List<Genre>>> =
        getListFromResponse(
            request = {genreDataSource.getMovieGenreList(queryMap)},
            mapper = {response->response.genres.map { it.mapToGenre() }}
        )

    override suspend fun getTvGenreList(): Flow<Resource<List<Genre>>> =
        getListFromResponse(
            request = {genreDataSource.getTvGenreList(queryMap)},
            mapper = {response->response.genres.map { it.mapToGenre() }}
        )


}