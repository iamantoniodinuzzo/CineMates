package com.indisparte.tv.repository

import com.indisparte.filter.TimeWindow
import com.indisparte.person.Cast
import com.indisparte.common.CountryResult
import com.indisparte.person.Crew
import com.indisparte.tv.EpisodeGroup
import com.indisparte.tv.EpisodeGroupDetails
import com.indisparte.tv.SeasonDetails
import com.indisparte.tv.TvShow
import com.indisparte.tv.TvShowDetails
import com.indisparte.common.Video
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

    fun getCast(id: Int): Flow<Result<List<Cast>>>
    fun getCrew(id: Int): Flow<Result<List<Crew>>>
    fun getEpisodeGroup(id: Int): Flow<Result<List<EpisodeGroup>>>
    fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Result<EpisodeGroupDetails>>
    fun getSeasonDetails(tvId: Int, seasonNumber: Int): Flow<Result<SeasonDetails>>
    fun getWatchProviders(
        tvId: Int,
        country: String,
    ): Flow<Result<CountryResult?>>

    fun getVideos(tvId: Int): Flow<Result<List<Video>>>
}