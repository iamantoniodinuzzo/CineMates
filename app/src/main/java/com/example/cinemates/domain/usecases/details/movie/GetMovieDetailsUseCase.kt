package com.example.cinemates.domain.usecases.details.movie

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.credits.mapToCast
import com.example.cinemates.domain.mapper.credits.mapToCrew
import com.example.cinemates.domain.mapper.image.mapToImage
import com.example.cinemates.domain.mapper.movie.mapToCollection
import com.example.cinemates.domain.mapper.movie.mapToMedia
import com.example.cinemates.domain.mapper.movie.mapToMovie
import com.example.cinemates.domain.mapper.trailer.mapToVideo
import com.example.cinemates.domain.model.movie.Collection
import com.example.cinemates.domain.model.common.*
import com.example.cinemates.domain.model.credits.Cast
import com.example.cinemates.domain.model.credits.Crew
import com.example.cinemates.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetMovieDetailsUseCase
@Inject
constructor(
    private val movieRepository: MovieRepository,
) {
    fun getMovieDetails(movieId: Int): Flow<Movie> {
        return movieRepository.getDetails(movieId).map { it.mapToMovie() }
    }

    fun getMovieCast(movieId: Int): Flow<List<Cast>> {
        return movieRepository.getCast(movieId).map { castDTOList ->
            castDTOList.map { it.mapToCast() }
        }
    }

    fun getMovieCrew(movieId: Int): Flow<List<Crew>> {
        return movieRepository.getCrew(movieId).map { creDTOList ->
            creDTOList.map { it.mapToCrew() }
        }
    }

    fun getSimilarMovies(movieId: Int): Flow<List<Media>> {
        return movieRepository.getSimilar(movieId).map { movieDTOList ->
            movieDTOList.map { it.mapToMedia() }
        }
    }

    fun getPosters(movieId: Int): Flow<List<Image>> {
        return movieRepository.getPosters(movieId).map { imageDTOList ->
            imageDTOList.map { it.mapToImage() }
        }
    }

    fun getBackdrops(movieId: Int): Flow<List<Image>> {
        return movieRepository.getBackdrops(movieId).map { imageDTOList ->
            imageDTOList.map { it.mapToImage() }
        }
    }

    fun getTrailers(movieId: Int): Flow<List<Video>> {
        return movieRepository.getVideos(movieId).map { videoDTOList ->
            videoDTOList.map { it.mapToVideo() }
        }
    }

    fun getCollection(collectionId: Int): Flow<Collection> {
        return movieRepository.getCollection(collectionId).map { it.mapToCollection() }
    }
}