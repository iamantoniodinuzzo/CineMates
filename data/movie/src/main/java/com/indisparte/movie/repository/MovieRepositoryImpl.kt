package com.indisparte.movie.repository

import com.indisparte.model.entity.Movie
import com.indisparte.movie.mapper.toMovie
import com.indisparte.movie.source.MovieDataSource
import com.indisparte.movie.util.MediaFilter
import com.indisparte.movie.util.MediaListSpecification
import com.indisparte.movie.util.TimeWindow
import com.indisparte.network.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class MovieRepositoryImpl
@Inject
constructor(
    private val movieService: MovieDataSource,
    private val queryMap: MutableMap<String, String>,
) : MovieRepository {

    override suspend fun getSpecificList(specification: MediaListSpecification): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getListOfSpecificMovies(specification.value, queryMap)
                if (response.isSuccessful) {
                    val specificListResponse = response.body()
                    if (specificListResponse != null) {
                        val movies = specificListResponse.results.map { it.toMovie() }
                        emit(Resource.Success(movies)) // Emit success state with the retrieved products
                    } else {
                        emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody ?: response.message()
                    emit(Resource.Error(Exception(errorMessage))) // Emit error state with the error message
                }
            } catch (e: Exception) {
                emit(Resource.Error(e)) // Emit error state with the exception
            }
        }


    override suspend fun getTrending(timeWindow: TimeWindow): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getTrending(timeWindow.value, queryMap)
                if (response.isSuccessful) {
                    val trendingMovies = response.body()
                    if (trendingMovies != null) {
                        val movies = trendingMovies.results.map { it.toMovie() }
                        emit(Resource.Success(movies)) // Emit success state with the retrieved products
                    } else {
                        emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody ?: response.message()
                    emit(Resource.Error(Exception(errorMessage))) // Emit error state with the error message
                }
            } catch (e: Exception) {
                emit(Resource.Error(e)) // Emit error state with the exception
            }
        }

    override suspend fun getBySearch(query: String): Flow<Resource<List<Movie>>> =
        flow {
            queryMap["query"] = query
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getBySearch(queryMap)
                if (response.isSuccessful) {
                    val bySearch = response.body()
                    if (bySearch != null) {
                        val movies = bySearch.results.map { it.toMovie() }
                        emit(Resource.Success(movies)) // Emit success state with the retrieved products
                    } else {
                        emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody ?: response.message()
                    emit(Resource.Error(Exception(errorMessage))) // Emit error state with the error message
                }
            } catch (e: Exception) {
                emit(Resource.Error(e)) // Emit error state with the exception
            }
        }

    override suspend fun getDiscoverable(mediaFilter: MediaFilter): Flow<Resource<List<Movie>>> =
        flow {
            mediaFilter.apply {
                sortBy?.let {
                    queryMap["sort_by"] = it.toString()
                }
                genresIdAsString?.let {
                    queryMap["with_genres"] = it
                }
                year?.let {
                    queryMap["year"] = it.toString()
                }
            }
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getByDiscover(queryMap)
                if (response.isSuccessful) {
                    val moviesByDiscover = response.body()
                    if (moviesByDiscover != null) {
                        val movies = moviesByDiscover.results.map { it.toMovie() }
                        emit(Resource.Success(movies)) // Emit success state with the retrieved products
                    } else {
                        emit(Resource.Error(Exception("Empty response"))) // Emit error state for empty response
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = errorBody ?: response.message()
                    emit(Resource.Error(Exception(errorMessage))) // Emit error state with the error message
                }
            } catch (e: Exception) {
                emit(Resource.Error(e)) // Emit error state with the exception
            }
        }


}