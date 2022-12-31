package com.example.cinemates.repository

import com.example.cinemates.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import com.example.cinemates.api.service.TvShowService

/**
 * @author Antonio Di Nuzzo
 */
class TvShowRepository
@Inject
constructor(
    private val tvShowService: TvShowService
) {
    private val defaultSystemLanguage: String = Locale.getDefault().language

    companion object {
        private lateinit var sMap: HashMap<String, String>
    }

    init {
        sMap = HashMap()
        sMap["language"] =
            defaultSystemLanguage
        sMap["append_to_response"] = "images"
        sMap["include_image_language"] =
            defaultSystemLanguage
        sMap["page"] = "1"
    }

     fun getPopularTvShow(): Flow<List<TvShow>> = flow {
        val popular = tvShowService.getPopular(sMap).results
        emit(popular)
    }

     fun getOnTheAir(): Flow<List<TvShow>> = flow {
        val onAir = tvShowService.getOnTheAir(sMap).results
        emit(onAir)
    }

     fun getGenreList(): Flow<List<Genre>> = flow {
        val genres = tvShowService.getGenreList(sMap).results
        emit(genres)
    }

     fun getTrendingTvShow(mediaType: String, timeWindow: String): Flow<List<TvShow>> =
        flow {
            val trending = tvShowService.getTrendingMedia(mediaType, timeWindow, sMap).results
            emit(trending)
        }


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = tvShowService.getVideos(movieId, sMap).results
        emit(videos)
    }

    fun getTvShowDetails(movieId: Int): Flow<TvShow> = flow {
        emit(tvShowService.getMovieDetails(movieId, sMap))
    }


    fun getSimilarTvShow(movieId: Int): Flow<List<TvShow>> = flow {
        val similarMovies = tvShowService.getSimilar(movieId, sMap).results
        emit(similarMovies)
    }

    fun getDiscoverableTvShow(filter: Filter): Flow<List<TvShow>> = flow {
        sMap["sort_by"] =
            filter.sortBy.toString()
        sMap["with_genres"] =
            filter.withGenres
                .toString()
                .replace("[", "")
                .replace("]", "")
        val movies = tvShowService.getTvShowByDiscover(sMap).results
        emit(movies)
    }

    fun getPosters(movieId: Int): Flow<List<Image>> = flow {
        val posters = tvShowService.getImages(movieId, sMap).posters
        emit(posters)
    }

    fun getBackdrops(movieId: Int): Flow<List<Image>> = flow {
        val backdrops = tvShowService.getImages(movieId, sMap).backdrops
        emit(backdrops)
    }

    fun getTvShowCast(movieId: Int): Flow<List<Cast>> = flow {
        val cast = tvShowService.getMovieCredits(movieId, sMap).cast
        emit(cast)
    }

    fun getTvShowCrew(movieId: Int): Flow<List<Crew>> = flow {
        val cast = tvShowService.getMovieCredits(movieId, sMap).crew
        emit(cast)
    }


     fun getTvShowBySearch(query: String): Flow<List<TvShow>> = flow {
        sMap["query"] = query
        emit(tvShowService.getTvShowBySearch(sMap).results)
    }

   /*  fun getTvShowByActor(with_cast: String): Flow<List<TvShow>> = flow {
        sMap["with_cast"] = with_cast
        emit(tvShowService.getTvShowByDiscover(sMap).results)
    }
*/

}

