package com.example.cinemates.repositories

import com.example.cinemates.model.*
import com.example.cinemates.api.service.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.cinemates.model.Collection

/**
 * @author Antonio Di Nuzzo
 */
class MovieRepository
@Inject
constructor(
    private val movieService: MovieService,
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

    fun getTrending(timeWindow: String): Flow<List<Movie>> = flow {
        val trending = movieService.getTrending(timeWindow, queryMap).results
        emit(trending)
    }


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = movieService.getVideos(movieId, queryMap).results
        emit(videos)
    }

    fun getDetails(movieId: Int): Flow<Movie> = flow {
        emit(movieService.getDetails(movieId, queryMap))
    }


    fun getSimilar(movieId: Int): Flow<List<Movie>> = flow {
        val similarMovies = movieService.getSimilar(movieId, queryMap).results
        emit(similarMovies)
    }

    fun getRecommended(movieId: Int): Flow<List<Movie>> = flow {
        val recommendedMovies = movieService.getRecommended(movieId, queryMap).results
        emit(recommendedMovies)
    }

    fun getDiscoverable(filter: Filter): Flow<List<Movie>> = flow {
        queryMap["sort_by"] =
            filter.sortBy.toString()
        queryMap["with_genres"] =
            filter.withGenres
                .toString()
                .replace("[", "")
                .replace("]", "")
        val movies = movieService.getByDiscover(queryMap).results
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

    fun getCast(movieId: Int): Flow<List<Cast>> = flow {
        val cast = movieService.getCredits(movieId, queryMap).cast
        emit(cast)
    }

    fun getCrew(movieId: Int): Flow<List<Crew>> = flow {
        val cast = movieService.getCredits(movieId, queryMap).crew
        emit(cast)
    }


    fun getCollection(collectionId: Int): Flow<Collection> = flow {
        val collection = movieService.getCollection(collectionId, queryMap)
        emit(collection)
    }


    fun getBySearch(query: String): Flow<List<Movie>> = flow {
        queryMap["query"] = query
        emit(movieService.getBySearch(queryMap).results)
    }

    fun getByActor(with_cast: String): Flow<List<Movie>> = flow {
        queryMap["with_cast"] = with_cast
        emit(movieService.getByDiscover(queryMap).results)
    }


}
