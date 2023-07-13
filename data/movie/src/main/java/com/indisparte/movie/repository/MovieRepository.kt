package com.indisparte.movie.repository

import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie.util.MediaFilter
import com.indisparte.movie.util.MediaListSpecification
import com.indisparte.movie.util.TimeWindow
import com.indisparte.network.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieRepository {
    //Get Popular,TopRated and Upcoming movies
    suspend fun getSpecificList(specification: MediaListSpecification): Flow<Resource<List<Movie>>>

    suspend fun getTrending(timeWindow: TimeWindow): Flow<Resource<List<Movie>>>
    suspend fun getBySearch(query: String): Flow<Resource<List<Movie>>>
    suspend fun getDiscoverable(mediaFilter: MediaFilter): Flow<Resource<List<Movie>>>
    suspend fun getDetails(movieId: Int): Flow<Resource<MovieDetails>>
    suspend fun getSimilar(movieId: Int): Flow<Resource<List<Movie>>>
//    suspend fun getImages(movieId: Int): Flow<Resource<List<ImageDTO>>>
    suspend fun getCast(movieId: Int): Flow<Resource<List<Cast>>>
    suspend fun getCrew(movieId: Int): Flow<Resource<List<Crew>>>

}