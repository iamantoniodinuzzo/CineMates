package com.example.cinemates.repository

import com.example.cinemates.model.*
import com.example.cinemates.api.service.MovieService
import com.example.cinemates.local.dao.MovieDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import com.example.cinemates.model.Collection

/**
 * @author Antonio Di Nuzzo
 */
class MovieRepository
@Inject
constructor(
    private val movieService: MovieService,
    private val movieDao: MovieDao,
    private val queryMap: MutableMap<String, String>
) {

    /**
     * Get Popular,TopRated and Upcoming movies
     */
    fun getSpecificMovieList(specification: String) = flow {
        val movieList = movieService.getListOfSpecificMovies(specification, queryMap).results
        emit(movieList)
    }

    fun getGenreList(): Flow<List<Genre>> = flow {
        val genres = movieService.getGenreList(queryMap).results
        emit(genres)
    }

    fun getTrendingMovies(mediaType: String, timeWindow: String): Flow<List<Movie>> = flow {
        val trending = movieService.getTrendingMovies(mediaType, timeWindow, queryMap).results
        emit(trending)
    }


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = movieService.getVideos(movieId, queryMap).results
        emit(videos)
    }

    fun getMovieDetails(movieId: Int): Flow<Movie> = flow {
        emit(movieService.getMovieDetails(movieId, queryMap))
    }


    fun getSimilarMovies(movieId: Int): Flow<List<Movie>> = flow {
        val similarMovies = movieService.getSimilar(movieId, queryMap).results
        emit(similarMovies)
    }

    fun getRecommendedMovies(movieId: Int): Flow<List<Movie>> = flow {
        val recommendedMovies = movieService.getRecommended(movieId, queryMap).results
        emit(recommendedMovies)
    }

    fun getDiscoverableMovies(filter: Filter): Flow<List<Movie>> = flow {
        queryMap["sort_by"] =
            filter.sortBy.toString()
        queryMap["with_genres"] =
            filter.withGenres
                .toString()
                .replace("[", "")
                .replace("]", "")
        val movies = movieService.getMoviesByDiscover(queryMap).results
        emit(movies)
    }

    fun getPosters(movieId: Int): Flow<List<Image>> = flow {
        val posters = movieService.getImages(movieId, queryMap).posters
        emit(posters)
    }

    fun getBackdrops(movieId: Int): Flow<List<Image>> = flow {
        val backdrops = movieService.getImages(movieId, queryMap).backdrops
        emit(backdrops)
    }

    fun getMovieCast(movieId: Int): Flow<List<Cast>> = flow {
        val cast = movieService.getMovieCredits(movieId, queryMap).cast
        emit(cast)
    }

    fun getMovieCrew(movieId: Int): Flow<List<Crew>> = flow {
        val cast = movieService.getMovieCredits(movieId, queryMap).crew
        emit(cast)
    }


    fun getCollection(collectionId: Int): Flow<Collection> = flow {
        val collection = movieService.getCollection(collectionId, queryMap)
        emit(collection)
    }


    fun getMoviesBySearch(query: String): Flow<List<Movie>> = flow {
        queryMap["query"] = query
        emit(movieService.getMoviesBySearch(queryMap).results)
    }

    fun getMoviesByActor(with_cast: String): Flow<List<Movie>> = flow {
        queryMap["with_cast"] = with_cast
        emit(movieService.getMoviesByDiscover(queryMap).results)
    }


    fun getMovies() = movieDao.getAll()
    fun getMovie(id: Int) = movieDao.getById(id)
    fun getToSeeMovies() = movieDao.getMoviesWithThisPersonalStatus(PersonalStatus.TO_SEE)
    fun getSeenMovies() = movieDao.getMoviesWithThisPersonalStatus(PersonalStatus.SEEN)
    fun getFavoriteMovies() = movieDao.getFavoriteMovies()
    fun isMovieFavorite(id: Int) = movieDao.isFavorite(id)
    fun isMovieToSee(id: Int) = movieDao.isThisPersonalStatus(id,PersonalStatus.TO_SEE)
    fun isMovieSeen(id: Int) = movieDao.isThisPersonalStatus(id,PersonalStatus.SEEN)
    suspend fun insertMovie(movie: Movie) = movieDao.insert(movie)
    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)


}

