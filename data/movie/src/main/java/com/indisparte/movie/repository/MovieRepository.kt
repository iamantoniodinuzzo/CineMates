package com.indisparte.movie.repository

import com.indisparte.model.entity.Movie
import com.indisparte.movie.response.MovieDTO
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

}