package com.indisparte.tv.repository

import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.filter.TimeWindow
import com.indisparte.network.util.Result
import com.indisparte.network.response.getListFromResponse
import com.indisparte.network.response.getSingleFromResponse
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import com.indisparte.tv.EpisodeGroup
import com.indisparte.tv.EpisodeGroupDetails
import com.indisparte.tv.SeasonDetails
import com.indisparte.tv.TvShow
import com.indisparte.tv.TvShowDetails
import com.indisparte.tv.mapper.mapToCast
import com.indisparte.tv.mapper.mapToCountryResult
import com.indisparte.tv.mapper.mapToCrew
import com.indisparte.tv.mapper.mapToEpisodeGroup
import com.indisparte.tv.mapper.mapToEpisodeGroupDetails
import com.indisparte.tv.mapper.mapToSeasonDetails
import com.indisparte.tv.mapper.mapToTvShow
import com.indisparte.tv.mapper.mapToTvShowDetails
import com.indisparte.tv.mapper.mapToVideo
import com.indisparte.tv.source.TvDataSource
import com.indisparte.tv.util.TvListType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
class TvRepositoryImpl
@Inject
constructor(
    private val tvDataSource: TvDataSource,
    private val queryMap: MutableMap<String, String>,
) : TvRepository {

    override fun getSpecificTVList(tvListType: TvListType): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getTvListByType(tvListType.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override fun getTrending(timeWindow: TimeWindow): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getTrending(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override fun getDetails(id: Int): Flow<Result<TvShowDetails>> =
        getSingleFromResponse(
            request = { tvDataSource.getDetails(id, queryMap) },
            mapper = { response -> response.mapToTvShowDetails() }
        )

    override fun getSimilar(id: Int): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getSimilar(id, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )


    override fun getCast(id: Int): Flow<Result<List<Cast>>> =
        getListFromResponse(
            request = { tvDataSource.getCredits(id, queryMap) },
            mapper = { response -> response.cast.map { it.mapToCast() } }
        )

    override fun getCrew(id: Int): Flow<Result<List<Crew>>> =
        getListFromResponse(
            request = { tvDataSource.getCredits(id, queryMap) },
            mapper = { response -> response.crew.map { it.mapToCrew() } }
        )

    override fun getEpisodeGroup(id: Int): Flow<Result<List<EpisodeGroup>>> =
        getListFromResponse(
            request = { tvDataSource.getEpisodesGroup(id, queryMap) },
            mapper = { response -> response.results.map { it.mapToEpisodeGroup() } }
        )

    override fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Result<EpisodeGroupDetails>> =
        getSingleFromResponse(
            request = { tvDataSource.getEpisodeGroupDetails(episodeGroupId, queryMap) },
            mapper = { response -> response.mapToEpisodeGroupDetails() }
        )

    override fun getSeasonDetails(
        tvId: Int,
        seasonNumber: Int,
    ): Flow<Result<SeasonDetails>> = getSingleFromResponse(
        request = { tvDataSource.getSeasonDetails(tvId, seasonNumber, queryMap) },
        mapper = { response -> response.mapToSeasonDetails() }
    )

    override fun getWatchProviders(
        tvId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> = getSingleFromResponse(
        request = { tvDataSource.getWatchProviders(tvId, queryMap) },
        mapper = { response ->
            response.getCountryResultByCountry(country)?.mapToCountryResult()
        }
    )

    override fun getVideos(tvId: Int): Flow<Result<List<Video>>> =
        getListFromResponse(
            request = { tvDataSource.getVideos(tvId, queryMap) },
            mapper = { response -> response.results.map { it.mapToVideo() } }
        )
}