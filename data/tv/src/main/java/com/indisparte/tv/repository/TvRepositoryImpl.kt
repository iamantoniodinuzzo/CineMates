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
import com.indisparte.network.getListFromResponse
import com.indisparte.network.getSingleFromResponse
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
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class TvRepositoryImpl
@Inject
constructor(
    private val tvDataSource: TvDataSource,
    private val queryMap: MutableMap<String, String>,
) : TvRepository {

    //todo need to cache this tv
    override  fun getSpecificTVList(tvListType: TvListType): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getTvListByType(tvListType.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override  fun getTrending(timeWindow: TimeWindow): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getTrending(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override  fun getDetails(id: Int): Flow<Result<TvShowDetails>> =
        getSingleFromResponse(
            request = { tvDataSource.getDetails(id, queryMap) },
            mapper = { response -> response.mapToTvShowDetails() }
        )

    override  fun getSimilar(id: Int): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getSimilar(id, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override  fun getDiscoverable(tvFilter: MediaFilter): Flow<Result<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getByDiscover(createQueryParams(tvFilter)) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    private fun createQueryParams(tvFilter: MediaFilter): Map<String, String> {
        val queryParams = mutableMapOf<String, String>()

        tvFilter.sortBy?.let {
            queryMap["sort_by"] = it.toString()
        }
        tvFilter.genresIdAsString?.let {
            queryMap["with_genres"] = it
        }
        tvFilter.year?.let {
            queryMap["first_air_date_year"] = it.toString()
        }
        return queryParams
    }

    /* override  fun getPosters(id: Int): Flow<Result<List<Image>>> =
                  TODO("Not yet implemented")

     override  fun getBackdrops(id: Int): Flow<Result<List<Image>>> {
         TODO("Not yet implemented")
     }*/

    override  fun getCast(id: Int): Flow<Result<List<Cast>>> =
        getListFromResponse(
            request = { tvDataSource.getCredits(id, queryMap) },
            mapper = { response -> response.cast.map { it.mapToCast() } }
        )

    override  fun getCrew(id: Int): Flow<Result<List<Crew>>> =
        getListFromResponse(
            request = { tvDataSource.getCredits(id, queryMap) },
            mapper = { response -> response.crew.map { it.mapToCrew() } }
        )

    override  fun getBySearch(query: String): Flow<Result<List<TvShow>>> =
        flow {
            queryMap["query"] = query
            emitAll(getListFromResponse(
                request = { tvDataSource.getBySearch(queryMap) },
                mapper = { response -> response.results.map { it.mapToTvShow() } }
            ))
        }

    override  fun getEpisodeGroup(id: Int): Flow<Result<List<EpisodeGroup>>> =
        getListFromResponse(
            request = { tvDataSource.getEpisodesGroup(id, queryMap) },
            mapper = { response -> response.results.map { it.mapToEpisodeGroup() } }
        )

    override  fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Result<EpisodeGroupDetails>> =
        getSingleFromResponse(
            request = { tvDataSource.getEpisodeGroupDetails(episodeGroupId, queryMap) },
            mapper = { response -> response.mapToEpisodeGroupDetails() }
        )

    override  fun getSeasonDetails(
        tvId: Int,
        seasonNumber: Int,
    ): Flow<Result<SeasonDetails>> = getSingleFromResponse(
        request = { tvDataSource.getSeasonDetails(tvId, seasonNumber, queryMap) },
        mapper = { response -> response.mapToSeasonDetails() }
    )

    override  fun getWatchProviders(
        tvId: Int,
        country: String,
    ): Flow<Result<CountryResult?>> = getSingleFromResponse(
        request = { tvDataSource.getWatchProviders(tvId, queryMap) },
        mapper = { response ->
            response.getCountryResultByCountry(country)?.mapToCountryResult()
        }
    )

    override  fun getVideos(tvId: Int): Flow<Result<List<Video>>> =
        getListFromResponse(
            request = { tvDataSource.getVideos(tvId, queryMap) },
            mapper = { response -> response.results.map { it.mapToVideo() } }
        )
}