package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.common.GenreDTO
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.movie.CollectionDTO
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.domain.model.Filter
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface MovieRepository {

    //Get Popular,TopRated and Upcoming movies
    fun getSpecificMovieList(specification: String): Flow<List<MovieDTO>>
    fun getGenreList(): Flow<List<GenreDTO>>
    fun getTrending(timeWindow: String): Flow<List<MovieDTO>>
    fun getVideos(movieId: Int): Flow<List<VideoDTO>>
    fun getDetails(movieId: Int): Flow<MovieDTO>
    fun getSimilar(movieId: Int): Flow<List<MovieDTO>>
    fun getRecommended(movieId: Int): Flow<List<MovieDTO>>
    fun getDiscoverable(filter: Filter): Flow<List<MovieDTO>>
    fun getPosters(movieId: Int): Flow<List<ImageDTO>>
    fun getImages(movieId: Int): Flow<List<ImageDTO>>
    fun getBackdrops(movieId: Int): Flow<List<ImageDTO>>
    fun getCast(movieId: Int): Flow<List<CastDTO>>
    fun getCrew(movieId: Int): Flow<List<CrewDTO>>
    fun getCollection(collectionId: Int): Flow<CollectionDTO>
    fun getBySearch(query: String): Flow<List<MovieDTO>>
    fun getByActor(with_cast: String): Flow<List<MovieDTO>>
}