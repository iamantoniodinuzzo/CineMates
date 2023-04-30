package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.data.remote.response.tvShow.*
import com.example.cinemates.domain.model.common.Filter
import com.example.cinemates.util.MediaListSpecification
import com.example.cinemates.util.TimeWindow
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface TvShowRepository {
    fun getSpecificTVList(specification: MediaListSpecification): Flow<List<TvShowDTO>>
    fun getTrending(timeWindow: TimeWindow): Flow<List<TvShowDTO>>
    fun getVideos(id: Int): Flow<List<VideoDTO>>
    fun getDetails(id: Int): Flow<TvShowDetailsDTO>
    fun getSimilar(id: Int): Flow<List<TvShowDTO>>
    fun getDiscoverable(filter: Filter): Flow<List<TvShowDTO>>
    fun getPosters(id: Int): Flow<List<ImageDTO>>
    fun getBackdrops(id: Int): Flow<List<ImageDTO>>
    fun getCast(id: Int): Flow<List<CastDTO>>
    fun getCrew(id: Int): Flow<List<CrewDTO>>
    fun getBySearch(query: String): Flow<List<TvShowDTO>>
    fun getEpisodeGroup(id: Int): Flow<List<EpisodeGroupDTO>>
    fun getEpisodeGroupDetails(episodeGroupId: String): Flow<EpisodeGroupDetailsDTO>
    fun getSeasonDetails(tvId:Int, seasonNumber:Int):Flow<SeasonDetailsDTO>
}