package com.example.cinemates.repositories

import com.example.cinemates.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.cinemates.api.service.TvShowService

/**
 * @author Antonio Di Nuzzo
 */
class TvShowRepository
@Inject
constructor(
    private val tvShowService: TvShowService,
    private val queryMap: MutableMap<String,String>
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


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = tvShowService.getVideos(movieId, queryMap).results
        emit(videos)
    }

    fun getDetails(movieId: Int): Flow<TvShow> = flow {
        emit(tvShowService.getDetails(movieId, queryMap))
    }


    fun getSimilar(movieId: Int): Flow<List<TvShow>> = flow {
        val similarMovies = tvShowService.getSimilar(movieId, queryMap).results
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

    fun getPosters(movieId: Int): Flow<List<Image>> = flow {
        val posters = tvShowService.getImages(movieId, queryMap).posters
        emit(posters)
    }

    fun getBackdrops(movieId: Int): Flow<List<Image>> = flow {
        val backdrops = tvShowService.getImages(movieId, queryMap).backdrops
        emit(backdrops)
    }

    fun getCast(movieId: Int): Flow<List<Cast>> = flow {
        val cast = tvShowService.getCredits(movieId, queryMap).cast
        emit(cast)
    }

    fun getCrew(movieId: Int): Flow<List<Crew>> = flow {
        val cast = tvShowService.getCredits(movieId, queryMap).crew
        emit(cast)
    }


    fun getBySearch(query: String): Flow<List<TvShow>> = flow {
        queryMap["query"] = query
        emit(tvShowService.getBySearch(queryMap).results)
    }

}

