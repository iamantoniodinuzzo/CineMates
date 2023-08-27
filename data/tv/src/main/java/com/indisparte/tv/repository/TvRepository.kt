package com.indisparte.tv.repository

import com.indisparte.model.entity.filter.MediaFilter
import com.indisparte.model.entity.filter.TimeWindow
import com.indisparte.model.entity.person.Cast
import com.indisparte.model.entity.common.CountryResult
import com.indisparte.model.entity.person.Crew
import com.indisparte.model.entity.tv.EpisodeGroup
import com.indisparte.model.entity.tv.EpisodeGroupDetails
import com.indisparte.model.entity.tv.SeasonDetails
import com.indisparte.model.entity.tv.TvShow
import com.indisparte.model.entity.tv.TvShowDetails
import com.indisparte.model.entity.common.Video
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