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
import com.indisparte.network.Resource
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
    override suspend fun getSpecificTVList(tvListType: TvListType): Flow<Resource<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getTvListByType(tvListType.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override suspend fun getTrending(timeWindow: TimeWindow): Flow<Resource<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getTrending(timeWindow.value, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override suspend fun getDetails(id: Int): Flow<Resource<TvShowDetails>> =
        getSingleFromResponse(
            request = { tvDataSource.getDetails(id, queryMap) },
            mapper = { response -> response.mapToTvShowDetails() }
        )

    override suspend fun getSimilar(id: Int): Flow<Resource<List<TvShow>>> =
        getListFromResponse(
            request = { tvDataSource.getSimilar(id, queryMap) },
            mapper = { response -> response.results.map { it.mapToTvShow() } }
        )

    override suspend fun getDiscoverable(tvFilter: MediaFilter): Flow<Resource<List<TvShow>>> =
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

    /* override suspend fun getPosters(id: Int): Flow<Resource<List<Image>>> =
                  TODO("Not yet implemented")

     override suspend fun getBackdrops(id: Int): Flow<Resource<List<Image>>> {
         TODO("Not yet implemented")
     }*/

    override suspend fun getCast(id: Int): Flow<Resource<List<Cast>>> =
        getListFromResponse(
            request = { tvDataSource.getCredits(id, queryMap) },
            mapper = { response -> response.cast.map { it.mapToCast() } }
        )

    override suspend fun getCrew(id: Int): Flow<Resource<List<Crew>>> =
        getListFromResponse(
            request = { tvDataSource.getCredits(id, queryMap) },
            mapper = { response -> response.crew.map { it.mapToCrew() } }
        )

    override suspend fun getBySearch(query: String): Flow<Resource<List<TvShow>>> =
        flow {
            queryMap["query"] = query
            emitAll(getListFromResponse(
                request = { tvDataSource.getBySearch(queryMap) },
                mapper = { response -> response.results.map { it.mapToTvShow() } }
            ))
        }

    override suspend fun getEpisodeGroup(id: Int): Flow<Resource<List<EpisodeGroup>>> =
        getListFromResponse(
            request = { tvDataSource.getEpisodesGroup(id, queryMap) },
            mapper = { response -> response.results.map { it.mapToEpisodeGroup() } }
        )

    override suspend fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Resource<EpisodeGroupDetails>> =
        getSingleFromResponse(
            request = { tvDataSource.getEpisodeGroupDetails(episodeGroupId, queryMap) },
            mapper = { response -> response.mapToEpisodeGroupDetails() }
        )

    override suspend fun getSeasonDetails(
        tvId: Int,
        seasonNumber: Int,
    ): Flow<Resource<SeasonDetails>> = getSingleFromResponse(
        request = { tvDataSource.getSeasonDetails(tvId, seasonNumber, queryMap) },
        mapper = { response -> response.mapToSeasonDetails() }
    )

    override suspend fun getWatchProviders(
        tvId: Int,
        country: String,
    ): Flow<Resource<List<CountryResult>>> = getListFromResponse(
        request = { tvDataSource.getWatchProviders(tvId, queryMap) },
        mapper = { response ->
            response.getCountryResultByCountry(country).map { it.mapToCountryResult() }
        }
    )
}