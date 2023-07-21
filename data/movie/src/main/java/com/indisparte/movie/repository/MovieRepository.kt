package com.indisparte.movie.repository

import com.indisparte.model.MediaFilter
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.model.entity.ReleaseDate
import com.indisparte.model.entity.ReleaseDatesByCountry
import com.indisparte.model.entity.Video
import com.indisparte.movie.util.MovieListType
import com.indisparte.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieRepository {
    suspend fun getByListType(movieListType: MovieListType): Flow<Resource<List<Movie>>>
    suspend fun getTrending(timeWindow: TimeWindow): Flow<Resource<List<Movie>>>
    suspend fun getBySearch(query: String): Flow<Resource<List<Movie>>>
    suspend fun getDiscoverable(mediaFilter: MediaFilter): Flow<Resource<List<Movie>>>
    suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>>
    suspend fun getSimilar(movieId: Int): Flow<Resource<List<Movie>>>
    suspend fun getCast(movieId: Int): Flow<Resource<List<Cast>>>
    suspend fun getCrew(movieId: Int): Flow<Resource<List<Crew>>>
    suspend fun getWatchProviders(
        movieId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>>

    suspend fun getVideos(movieId: Int): Flow<Resource<List<Video>>>

    suspend fun getReleaseDates(movieId: Int): Flow<Resource<List<ReleaseDatesByCountry>>>

}