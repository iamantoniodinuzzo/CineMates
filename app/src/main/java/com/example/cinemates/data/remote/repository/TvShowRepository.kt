package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.data.remote.response.tvShow.EpisodeGroupDTO
import com.example.cinemates.data.remote.response.tvShow.TvShowDTO
import com.example.cinemates.domain.model.Filter
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface TvShowRepository {
    fun getSpecificTVList(specification: String): Flow<List<TvShowDTO>>
    fun getTrending(timeWindow: String): Flow<List<TvShowDTO>>
    fun getVideos(id: Int): Flow<List<VideoDTO>>
    fun getDetails(id: Int): Flow<TvShowDTO>
    fun getSimilar(id: Int): Flow<List<TvShowDTO>>
    fun getDiscoverable(filter: Filter): Flow<List<TvShowDTO>>
    fun getPosters(id: Int): Flow<List<ImageDTO>>
    fun getBackdrops(id: Int): Flow<List<ImageDTO>>
    fun getCast(id: Int): Flow<List<CastDTO>>
    fun getCrew(id: Int): Flow<List<CrewDTO>>
    fun getBySearch(query: String): Flow<List<TvShowDTO>>
    fun getEpisodeGroup(id: Int): Flow<List<EpisodeGroupDTO>>
    fun getEpisodeGroupDetails(episodeGroupId: String): Flow<EpisodeGroupDTO>
}