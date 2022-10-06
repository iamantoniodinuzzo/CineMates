package com.example.cinemates.model.repository

import com.example.cinemates.model.api.MovieService
import com.example.cinemates.model.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.*
import javax.inject.Inject

private const val TAG = "MovieRepository"

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:46
 */
class MovieRepository
@Inject
constructor(private val apiService: MovieService) {
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

    suspend fun getPopularMovies() = apiService.getPopular(sMap)


    suspend fun getTopRatedMovies() = apiService.getTopRated(sMap)

    suspend fun getUpcomingMovies() = apiService.getUpcoming(sMap)

    suspend fun getCurrentlyShowingMovies() = apiService.getCurrentlyShowing(sMap)

    suspend fun getGenreList() = apiService.getGenreList(sMap)

    suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        apiService.getTrendingMovies(mediaType, timeWindow, sMap)


    suspend fun getTrendingPerson(mediaType: String, timeWindow: String) =
        apiService.getTrendingPerson(mediaType, timeWindow, sMap)


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = apiService.getVideos(movieId, sMap).body()?.results ?: listOf()
        emit(videos)
    }

    fun getMovieDetails(movieId: Int): Flow<Movie> = flow {
        val response = apiService.getMovieDetails(movieId, sMap)
        if (response.isSuccessful)
            response.body()?.let { movie ->
                emit(movie)
            }
    }


    fun getSimilarMovies(movieId: Int): Flow<List<Movie>> = flow {
        val similarMovies = apiService.getSimilar(movieId, sMap).body()?.results ?: listOf()
        emit(similarMovies)
    }

    fun getDiscoverableMovies(filter: Filter): Flow<List<Movie>> = flow {
        sMap["sort_by"] = filter.sortBy.toString()
        sMap["with_genres"] = filter.withGenres ?: ""
        val movies = apiService.getMoviesByDiscover(sMap).body()?.results ?: listOf()
        emit(movies)
    }


    suspend fun getReviews(movieId: Int) = apiService.getReviews(movieId, sMap)


    fun getImages(movieId: Int) = flow {
        val images = apiService.getImages(movieId, sMap).body()
        emit(images)
    }


    fun getMovieCredits(movieId: Int): Flow<CreditsResponse> = flow {
        val credits = apiService.getMovieCredits(movieId, sMap).body()
        if (credits != null)
            emit(credits)
    }


    suspend fun getActorDetails(personId: Int) = apiService.getActorDetails(personId, sMap)


    fun getCollection(collectionId: Int): Flow<List<Movie>> = flow {
        val collection = apiService.getCollection(collectionId, sMap).body()?.parts
        if (collection != null)
            emit(collection)
    }


    suspend fun getMoviesBySearch(query: String): Response<GenericResponse<Movie>> {
        sMap["query"] = query
        return apiService.getMoviesBySearch(sMap)
    }

    suspend fun getMoviesByActor(with_cast: String): Response<GenericResponse<Movie>> {
        sMap["with_cast"] = with_cast
        return apiService.getMoviesByDiscover(sMap)
    }

    suspend fun getPeoplesBySearch(query: String): Response<GenericResponse<Cast>> {
        sMap["query"] = query
        return apiService.getPeoplesBySearch(sMap)
    }


}

