package com.indisparte.tv.repository

import com.indisparte.common.CountryResult
import com.indisparte.common.Video
import com.indisparte.filter.TimeWindow
import com.indisparte.network.Result
import com.indisparte.person.Cast
import com.indisparte.person.Crew
import com.indisparte.tv.EpisodeGroup
import com.indisparte.tv.EpisodeGroupDetails
import com.indisparte.tv.SeasonDetails
import com.indisparte.tv.TvShow
import com.indisparte.tv.TvShowDetails
import com.indisparte.tv.util.TvListType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * @author Antonio Di Nuzzo
 */
class FakeTvRepository : TvRepository {
    private val tvShows = mutableListOf<TvShow>()
    private val tvShowDetailsMap = mutableMapOf<Int, TvShowDetails>()
    private val similarTvShowsMap = mutableMapOf<Int, List<TvShow>>()
    private val castMap = mutableMapOf<Int, List<Cast>>()
    private val crewMap = mutableMapOf<Int, List<Crew>>()
    private val episodeGroupMap = mutableMapOf<Int, List<EpisodeGroup>>()
    private val episodeGroupDetailsMap = mutableMapOf<String, EpisodeGroupDetails>()
    private val seasonDetailsMap = mutableMapOf<Pair<Int, Int>, SeasonDetails>()
    private val watchProvidersMap = mutableMapOf<Int, CountryResult?>()
    private val videosMap = mutableMapOf<Int, List<Video>>()


    // Helper methods to set fake data in the fake repository
    fun addTvShow(tvShow: TvShow) {
        tvShows.add(tvShow)
    }

    fun addTvShowDetails(id: Int, details: TvShowDetails) {
        tvShowDetailsMap[id] = details
    }

    fun addSimilarTvShows(id: Int, similarTvShows: List<TvShow>) {
        similarTvShowsMap[id] = similarTvShows
    }

    fun addCast(tvId: Int, cast: List<Cast>) {
        castMap[tvId] = cast
    }

    fun addCrew(tvId: Int, crew: List<Crew>) {
        crewMap[tvId] = crew
    }

    fun addEpisodeGroups(tvId: Int, episodeGroups: List<EpisodeGroup>) {
        episodeGroupMap[tvId] = episodeGroups
    }

    fun addEpisodeGroupDetails(episodeGroupId: String, episodeGroupDetails: EpisodeGroupDetails) {
        episodeGroupDetailsMap[episodeGroupId] = episodeGroupDetails
    }

    fun addSeasonDetails(tvId: Int, seasonNumber: Int, seasonDetails: SeasonDetails) {
        seasonDetailsMap[Pair(tvId, seasonNumber)] = seasonDetails
    }

    fun addWatchProviders(tvId: Int, countryResult: CountryResult?) {
        watchProvidersMap[tvId] = countryResult
    }

    fun addVideos(tvId: Int, videos: List<Video>) {
        videosMap[tvId] = videos
    }

    // Helper method to clear all fake data
    fun clearData() {
        tvShows.clear()
        tvShowDetailsMap.clear()
        similarTvShowsMap.clear()
        castMap.clear()
        crewMap.clear()
        episodeGroupMap.clear()
        episodeGroupDetailsMap.clear()
        seasonDetailsMap.clear()
        watchProvidersMap.clear()
        videosMap.clear()
    }

    override fun getSpecificTVList(tvListType: TvListType): Flow<Result<List<TvShow>>> {
        // Implement the behavior to return fake data for getSpecificTVList
        return flow { emit(Result.Success(tvShows)) }
    }

    override fun getTrending(timeWindow: TimeWindow): Flow<Result<List<TvShow>>> {
        // Implement the behavior to return fake data for getTrending
        return flow { emit(Result.Success(tvShows)) }
    }

    override fun getDetails(id: Int): Flow<Result<TvShowDetails>> {
        // Implement the behavior to return fake data for getDetails
        val fakeData = tvShowDetailsMap[id]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getSimilar(id: Int): Flow<Result<List<TvShow>>> {
        // Implement the behavior to return fake data for getSimilar
        val fakeData = similarTvShowsMap[id]
        return flow { emit(Result.Success(fakeData ?: emptyList())) }
    }

    override fun getCast(id: Int): Flow<Result<List<Cast>>> {
        val fakeData = castMap[id]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getCrew(id: Int): Flow<Result<List<Crew>>> {
        val fakeData = crewMap[id]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getEpisodeGroup(id: Int): Flow<Result<List<EpisodeGroup>>> {
        val fakeData = episodeGroupMap[id]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getEpisodeGroupDetails(episodeGroupId: String): Flow<Result<EpisodeGroupDetails>> {
        val fakeData = episodeGroupDetailsMap[episodeGroupId]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getSeasonDetails(tvId: Int, seasonNumber: Int): Flow<Result<SeasonDetails>> {
        val fakeData = seasonDetailsMap[Pair(tvId, seasonNumber)]!!
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getWatchProviders(tvId: Int, country: String): Flow<Result<CountryResult?>> {
        val fakeData = watchProvidersMap[tvId]
        return flow { emit(Result.Success(fakeData)) }
    }

    override fun getVideos(tvId: Int): Flow<Result<List<Video>>> {
        val fakeData = videosMap[tvId]!!
        return flow { emit(Result.Success(fakeData)) }
    }


}
