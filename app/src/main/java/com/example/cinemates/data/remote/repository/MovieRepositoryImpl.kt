package com.example.cinemates.data.remote.repository

import com.example.cinemates.data.remote.response.GenreDTO
import com.example.cinemates.data.remote.response.credits.CastDTO
import com.example.cinemates.data.remote.response.credits.CrewDTO
import com.example.cinemates.data.remote.response.image.ImageDTO
import com.example.cinemates.data.remote.response.movie.CollectionDTO
import com.example.cinemates.data.remote.response.movie.MovieDTO
import com.example.cinemates.data.remote.response.trailer.VideoDTO
import com.example.cinemates.data.remote.service.MovieService
import com.example.cinemates.domain.model.Filter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 */
class MovieRepositoryImpl
@Inject
constructor(
    private val movieService: MovieService,
    private val queryMap: MutableMap<String, String>
) : MovieRepository {

    /**
     * Get Popular,TopRated and Upcoming movies
     */
    override fun getSpecificMovieList(specification: String) = flow {
        val movieList = movieService.getListOfSpecificMovies(specification, queryMap).results
        emit(movieList)
    }

    override fun getGenreList(): Flow<List<GenreDTO>> = flow {
        val genres = movieService.getGenreList(queryMap).results
        emit(genres)
    }

    override fun getTrending(timeWindow: String): Flow<List<MovieDTO>> = flow {
        val trending = movieService.getTrending(timeWindow, queryMap).results
        emit(trending)
    }


    override fun getVideos(movieId: Int): Flow<List<VideoDTO>> = flow {
        val videos = movieService.getVideos(movieId, queryMap).results
        emit(videos)
    }

    override fun getDetails(movieId: Int): Flow<MovieDTO> = flow {
        emit(movieService.getDetails(movieId, queryMap))
    }


    override fun getSimilar(movieId: Int): Flow<List<MovieDTO>> = flow {
        val similarMovies = movieService.getSimilar(movieId, queryMap).results
        emit(similarMovies)
    }

    override fun getRecommended(movieId: Int): Flow<List<MovieDTO>> = flow {
        val recommendedMovies = movieService.getRecommended(movieId, queryMap).results
        emit(recommendedMovies)
    }

    override fun getDiscoverable(filter: Filter): Flow<List<MovieDTO>> = flow {
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

    override fun getPosters(movieId: Int): Flow<List<ImageDTO>> = flow {
        val posters = movieService.getImages(movieId, queryMap).posters
        emit(posters)
    }

    override fun getImages(movieId: Int):Flow<List<ImageDTO>> = flow {
        val posters = movieService.getImages(movieId, queryMap).posters
        val backdrop = movieService.getImages(movieId,queryMap).backdrops
        val result = (posters+backdrop).shuffled()
        emit(result)
    }
    override fun getBackdrops(movieId: Int): Flow<List<ImageDTO>> = flow {
        val backdrops = movieService.getImages(movieId, queryMap).backdrops
        emit(backdrops)
    }

    override fun getCast(movieId: Int): Flow<List<CastDTO>> = flow {
        val cast = movieService.getCredits(movieId, queryMap).cast
        emit(cast)
    }

    override fun getCrew(movieId: Int): Flow<List<CrewDTO>> = flow {
        val cast = movieService.getCredits(movieId, queryMap).crew
        emit(cast)
    }


    override fun getCollection(collectionId: Int): Flow<CollectionDTO> = flow {
        val collection = movieService.getCollection(collectionId, queryMap)
        emit(collection)
    }


    override fun getBySearch(query: String): Flow<List<MovieDTO>> = flow {
        queryMap["query"] = query
        emit(movieService.getBySearch(queryMap).results)
    }

    override fun getByActor(with_cast: String): Flow<List<MovieDTO>> = flow {
        queryMap["with_cast"] = with_cast
        emit(movieService.getByDiscover(queryMap).results)
    }


}

