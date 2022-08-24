package com.example.cinemates.model.repository

import javax.inject.Inject
import com.example.cinemates.model.api.MovieApiService
import com.example.cinemates.model.data.*
import com.example.cinemates.model.data.Collection
import com.example.cinemates.model.repository.MovieRepository
import com.example.cinemates.util.Constants
import com.google.gson.JsonObject
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import java.util.HashMap

private const val TAG = "MovieRepository"

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:46
 */
class MovieRepository
@Inject
constructor(private val apiService: MovieApiService) {

    companion object {
        private lateinit var sMap: HashMap<String, String>
    }

    init {
        sMap = HashMap()
        sMap["api_key"] = Constants.TMDB_API_KEY
        sMap["language"] =
            Constants.DEFAULT_SYSTEM_LANGUAGE
        sMap["append_to_response"] = "images"
        sMap["include_image_language"] =
            Constants.DEFAULT_SYSTEM_LANGUAGE
        sMap["page"] = "1"
    }

    suspend fun getPopularMovies() = apiService.getPopular(sMap)


    suspend fun getTopRatedMovies() = apiService.getTopRated(sMap)

    suspend fun getUpcomingMovies() = apiService.getUpcoming(sMap)

    suspend fun getCurrentlyShowingMovies() = apiService.getCurrentlyShowing(sMap)

    suspend fun getGenreList() = apiService.getGenreList(sMap)


    suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        apiService.getTrendingMovies(mediaType, timeWindow, sMap)


    suspend fun getTrendingPerson(mediaType: String, timeWindow: String) =
        apiService.getTrendingPerson(mediaType, timeWindow, sMap)


    suspend fun getVideos(movie_id: Int) = apiService.getVideos(movie_id, sMap)


    suspend fun getMovieDetails(movieId: Int) = apiService.getMovieDetails(movieId, sMap)


    suspend fun getSimilar(movieId: Int) = apiService.getSimilar(movieId, sMap)


    suspend fun getReviews(movieId: Int) = apiService.getReviews(movieId, sMap)


    suspend fun getImages(movieId: Int) = apiService.getImages(movieId, sMap)


    suspend fun getCast(movieId: Int) = apiService.getCast(movieId, sMap)


    suspend fun getActorDetails(personId: Int) = apiService.getActorDetails(personId, sMap)


    suspend fun getCollection(collectionId: Int) = apiService.getCollection(collectionId, sMap)


    suspend fun getMoviesBySearch(query: String): Response<List<Movie>> {
        sMap["query"] = query
        return apiService.getMoviesBySearch(sMap)
    }

    suspend fun getMoviesByActor(with_cast: String): Response<List<Movie>> {
        sMap["with_cast"] = with_cast
        return apiService.getMoviesByDiscover(sMap)
    }

    suspend fun getPeoplesBySearch(query: String): Response<List<Cast>> {
        sMap["query"] = query
        return apiService.getPeoplesBySearch(sMap)
    }

    suspend fun getDiscoverMovies(sort_option: String, genre_id: String): Response<List<Movie>> {
        sMap["sort_by"] = sort_option
        sMap["with_genres"] = genre_id
        return apiService.getMoviesByDiscover(sMap)
    }


}