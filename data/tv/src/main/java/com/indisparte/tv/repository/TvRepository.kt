package com.indisparte.tv.repository

import com.indisparte.model.MediaFilter
import com.indisparte.model.TimeWindow
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.CountryResult
import com.indisparte.model.entity.Crew
import com.indisparte.model.entity.EpisodeGroup
import com.indisparte.model.entity.EpisodeGroupDetails
import com.indisparte.model.entity.SeasonDetails
import com.indisparte.model.entity.TvShow
import com.indisparte.model.entity.TvShowDetails
import com.indisparte.model.entity.Video
import com.indisparte.network.Result
import com.indisparte.tv.util.TvListType
import kotlinx.coroutines.flow.Flow

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
interface TvRepository {
    fun getSpecificTVList(tvListType: TvListType): Flow<Result<List<TvShow>>>
    fun getTrending(timeWindow: TimeWindow): Flow<Result<List<TvShow>>>
    fun getDetails(id: Int): Flow<Result<TvShowDetails>>
    fun getSimilar(id: Int): Flow<Result<List<TvShow>>>
    fun getDiscoverable(tvFilter: MediaFilter): Flow<Result<List<TvShow>>>

    /*fun getPosters(id: Int): Flow<Result<List<Image>>>
    fun getBackdrops(id: Int): Flow<Result<List<Image>>>*/
    fun getCast(id: Int): Flow<Result<List<Cast>>>
    fun getCrew(id: Int): Flow<Result<List<Crew>>>
    fun getBySearch(query: String): Flow<Result<List<TvShow>>>
    fun getEpisodeGroup(id: Int): Flow<Result<List<EpisodeGroup>>>
    fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Result<EpisodeGroupDetails>>
    fun getSeasonDetails(tvId: Int, seasonNumber: Int): Flow<Result<SeasonDetails>>
    fun getWatchProviders(
        tvId: Int,
        country: String,
    ): Flow<Result<CountryResult?>>

    fun getVideos(tvId: Int): Flow<Result<List<Video>>>
}