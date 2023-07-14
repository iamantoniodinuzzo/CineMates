package com.indisparte.movie.repository

import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie.mapper.mapToCast
import com.indisparte.movie.mapper.mapToCrew
import com.indisparte.movie.mapper.toMovie
import com.indisparte.movie.mapper.toMovieDetails
import com.indisparte.movie.source.MovieDataSource
import com.indisparte.model.MediaFilter
import com.indisparte.movie.util.MovieListType
import com.indisparte.util.TimeWindow
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

    override suspend fun getByListType(movieListType: MovieListType): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getListOfSpecificMovies(movieListType.value, queryMap)
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


    override suspend fun getTrending(timeWindow: com.indisparte.util.TimeWindow): Flow<Resource<List<Movie>>> =
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

    override suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getDetails(movieId, queryMap)
                if (response.isSuccessful) {
                    val moviesByDiscover = response.body()
                    if (moviesByDiscover != null) {
                        val movieDetails = moviesByDiscover.toMovieDetails()
                        emit(Resource.Success(movieDetails)) // Emit success state with the retrieved products
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


    override suspend fun getSimilar(movieId: Int): Flow<Resource<List<Movie>>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getSimilar(movieId, queryMap)
                if (response.isSuccessful) {
                    val similarMovies = response.body()
                    if (similarMovies != null) {
                        val similarMoviesList = similarMovies.results.map { it.toMovie() }
                        emit(Resource.Success(similarMoviesList)) // Emit success state with the retrieved products
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

    override suspend fun getCast(movieId: Int): Flow<Resource<List<Cast>>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getCredits(movieId, queryMap)
                if (response.isSuccessful) {
                    val castResponse = response.body()
                    if (castResponse != null) {
                        val cast = castResponse.cast.map { it.mapToCast() }
                        emit(Resource.Success(cast)) // Emit success state with the retrieved products
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

    override suspend fun getCrew(movieId: Int): Flow<Resource<List<Crew>>> =
        flow {
            emit(Resource.Loading()) // Emit loading state

            try {
                val response = movieService.getCredits(movieId, queryMap)
                if (response.isSuccessful) {
                    val crewResponse = response.body()
                    if (crewResponse != null) {
                        val crew = crewResponse.crew.map { it.mapToCrew() }
                        emit(Resource.Success(crew)) // Emit success state with the retrieved products
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
