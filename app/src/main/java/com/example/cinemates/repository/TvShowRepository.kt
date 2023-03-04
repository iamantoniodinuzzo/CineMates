package com.example.cinemates.repository

import com.example.cinemates.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import com.example.cinemates.api.service.TvShowService
import retrofit2.http.QueryMap
import kotlin.collections.HashMap

/**
 * @author Antonio Di Nuzzo
 */
class TvShowRepository
@Inject
constructor(
    private val tvShowService: TvShowService,
    private val queryMap: HashMap<String,String>
) {

    fun getPopularTvShow(): Flow<List<TvShow>> = flow {
        val popular = tvShowService.getPopular(queryMap).results
        emit(popular)
    }

    fun getOnTheAir(): Flow<List<TvShow>> = flow {
        val onAir = tvShowService.getOnTheAir(queryMap).results
        emit(onAir)
    }


    fun getTrendingTvShow(mediaType: String, timeWindow: String): Flow<List<TvShow>> =
        flow {
            val trending = tvShowService.getTrendingMedia(mediaType, timeWindow, queryMap).results
            emit(trending)
        }


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = tvShowService.getVideos(movieId, queryMap).results
        emit(videos)
    }

    fun getTvShowDetails(movieId: Int): Flow<TvShow> = flow {
        emit(tvShowService.getMovieDetails(movieId, queryMap))
    }


    fun getSimilarTvShow(movieId: Int): Flow<List<TvShow>> = flow {
        val similarMovies = tvShowService.getSimilar(movieId, queryMap).results
        emit(similarMovies)
    }

    fun getDiscoverableTvShow(filter: Filter): Flow<List<TvShow>> = flow {
        queryMap["sort_by"] =
            filter.sortBy.toString()
        queryMap["with_genres"] =
            filter.withGenres
                .toString()
                .replace("[", "")
                .replace("]", "")
        val movies = tvShowService.getTvShowByDiscover(queryMap).results
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

    fun getTvShowCast(movieId: Int): Flow<List<Cast>> = flow {
        val cast = tvShowService.getMovieCredits(movieId, queryMap).cast
        emit(cast)
    }

    fun getTvShowCrew(movieId: Int): Flow<List<Crew>> = flow {
        val cast = tvShowService.getMovieCredits(movieId, queryMap).crew
        emit(cast)
    }


    fun getTvShowBySearch(query: String): Flow<List<TvShow>> = flow {
        queryMap["query"] = query
        emit(tvShowService.getTvShowBySearch(queryMap).results)
    }

    /*  fun getTvShowByActor(with_cast: String): Flow<List<TvShow>> = flow {
         sMap["with_cast"] = with_cast
         emit(tvShowService.getTvShowByDiscover(sMap).results)
     }
 */

}

