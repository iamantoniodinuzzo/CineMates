package com.example.cinemates.repositories

import com.example.cinemates.api.service.TvShowService
import com.example.cinemates.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 */
class TvShowRepository
@Inject
constructor(
    private val tvShowService: TvShowService,
    private val queryMap: MutableMap<String, String>
) {

    fun getSpecificTVList(specification: String) = flow {
        val movieList = tvShowService.getListOfSpecificTv(specification, queryMap).results
        emit(movieList)
    }

    fun getTrending(timeWindow: String): Flow<List<TvShow>> =
        flow {
            val trending = tvShowService.getTrending(timeWindow, queryMap).results
            emit(trending)
        }


    fun getVideos(id: Int): Flow<List<Video>> = flow {
        val videos = tvShowService.getVideos(id, queryMap).results
        emit(videos)
    }

    fun getDetails(id: Int): Flow<TvShow> = flow {
        emit(tvShowService.getDetails(id, queryMap))
    }


    fun getSimilar(id: Int): Flow<List<TvShow>> = flow {
        val similarMovies = tvShowService.getSimilar(id, queryMap).results
        emit(similarMovies)
    }

    fun getDiscoverable(filter: Filter): Flow<List<TvShow>> = flow {
        queryMap["sort_by"] =
            filter.sortBy.toString()
        queryMap["with_genres"] =
            filter.withGenres
                .toString()
                .replace("[", "")
                .replace("]", "")
        val movies = tvShowService.getByDiscover(queryMap).results
        emit(movies)
    }

    fun getPosters(id: Int): Flow<List<Image>> = flow {
        val posters = tvShowService.getImages(id, queryMap).posters
        emit(posters)
    }

    fun getBackdrops(id: Int): Flow<List<Image>> = flow {
        val backdrops = tvShowService.getImages(id, queryMap).backdrops
        emit(backdrops)
    }

    fun getCast(id: Int): Flow<List<Cast>> = flow {
        val cast = tvShowService.getCredits(id, queryMap).cast
        emit(cast)
    }

    fun getCrew(id: Int): Flow<List<Crew>> = flow {
        val cast = tvShowService.getCredits(id, queryMap).crew
        emit(cast)
    }


    fun getBySearch(query: String): Flow<List<TvShow>> = flow {
        queryMap["query"] = query
        emit(tvShowService.getBySearch(queryMap).results)
    }

    fun getEpisodeGroup(id: Int): Flow<List<EpisodeGroup>> = flow {
        val episodesGroup = tvShowService.getEpisodesGroup(id, queryMap).results
        emit(episodesGroup)
    }

    fun getEpisodeGroupDetails(episodeGroupId: String): Flow<EpisodeGroup> = flow {
        val episodesGroup = tvShowService.getEpisodeGroupDetails(episodeGroupId, queryMap)
        emit(episodesGroup)
    }

}

