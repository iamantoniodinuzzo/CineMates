package com.example.cinemates.model.repository

import com.example.cinemates.network.service.MovieService
import com.example.cinemates.model.entities.*
import com.example.cinemates.network.response.CreditsResponse
import com.example.cinemates.network.response.GenericResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.util.*
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 */
class MovieRepository
@Inject
constructor(
    private val movieService: MovieService
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

    suspend fun getPopularMovies() = movieService.getPopular(sMap)

    suspend fun getTopRatedMovies() = movieService.getTopRated(sMap)

    suspend fun getUpcomingMovies() = movieService.getUpcoming(sMap)

    suspend fun getCurrentlyShowingMovies() = movieService.getCurrentlyShowing(sMap)

    suspend fun getGenreList() = movieService.getGenreList(sMap)

    suspend fun getTrendingMovies(mediaType: String, timeWindow: String) =
        movieService.getTrendingMovies(mediaType, timeWindow, sMap)


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = movieService.getVideos(movieId, sMap).body()?.results ?: listOf()
        emit(videos)
    }

    fun getMovieDetails(movieId: Int): Flow<Movie> = flow {
        val response = movieService.getMovieDetails(movieId, sMap)
        if (response.isSuccessful)
            response.body()?.let { movie ->
                emit(movie)
            }
    }


    fun getSimilarMovies(movieId: Int): Flow<List<Movie>> = flow {
        val similarMovies = movieService.getSimilar(movieId, sMap).body()?.results ?: listOf()
        emit(similarMovies)
    }

    fun getDiscoverableMovies(filter: Filter): Flow<List<Movie>> = flow {
        sMap["sort_by"] =
            filter.sortBy.toString()
        sMap["with_genres"] =
            filter.withGenres
                .toString()
                .replace("[", "")
                .replace("]", "")
        val movies = movieService.getMoviesByDiscover(sMap).body()?.results ?: listOf()
        emit(movies)
    }


    fun getImages(movieId: Int) = flow {
        val images = movieService.getImages(movieId, sMap).body()
        emit(images)
    }


    fun getMovieCredits(movieId: Int): Flow<CreditsResponse> = flow {
        val credits = movieService.getMovieCredits(movieId, sMap).body()
        if (credits != null)
            emit(credits)
    }


    fun getCollection(collectionId: Int): Flow<List<Movie>> = flow {
        val collection = movieService.getCollection(collectionId, sMap).body()?.parts
        if (collection != null)
            emit(collection)
    }


    suspend fun getMoviesBySearch(query: String): Response<GenericResponse<Movie>> {
        sMap["query"] = query
        return movieService.getMoviesBySearch(sMap)
    }

    suspend fun getMoviesByActor(with_cast: String): Response<GenericResponse<Movie>> {
        sMap["with_cast"] = with_cast
        return movieService.getMoviesByDiscover(sMap)
    }


}

