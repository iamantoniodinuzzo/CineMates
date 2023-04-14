package com.example.cinemates.domain.usecases.details.movie

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.credits.CastMapper
import com.example.cinemates.domain.mapper.credits.CrewMapper
import com.example.cinemates.domain.mapper.image.ImageMapper
import com.example.cinemates.domain.mapper.movie.CollectionMapper
import com.example.cinemates.domain.mapper.movie.MovieDetailsMapper
import com.example.cinemates.domain.mapper.movie.MovieToMediaMapper
import com.example.cinemates.domain.mapper.trailer.TrailerMapper
import com.example.cinemates.domain.model.*
import com.example.cinemates.domain.model.Collection
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
    private val movieToMediaMapper: MovieToMediaMapper,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val castMapper: CastMapper,
    private val crewMapper: CrewMapper,
    private val imageMapper: ImageMapper,
    private val trailerMapper: TrailerMapper,
    private val collectionMapper: CollectionMapper
) {
    fun getMovieDetails(movieId: Int): Flow<Movie> {
        return movieRepository.getDetails(movieId).map(movieDetailsMapper::map)
    }

    fun getMovieCast(movieId: Int): Flow<List<Cast>> {
        return movieRepository.getCast(movieId).map {
            it.map(castMapper::map)
        }
    }

    fun getMovieCrew(movieId: Int): Flow<List<Crew>> {
        return movieRepository.getCrew(movieId).map {
            it.map(crewMapper::map)
        }
    }

    fun getSimilarMovies(movieId: Int): Flow<List<Media>> {
        return movieRepository.getSimilar(movieId).map {
            it.map(movieToMediaMapper::map)
        }
    }

    fun getPosters(movieId: Int): Flow<List<Image>> {
        return movieRepository.getPosters(movieId).map {
            it.map(imageMapper::map)
        }
    }

    fun getBackdrops(movieId: Int): Flow<List<Image>> {
        return movieRepository.getBackdrops(movieId).map {
            it.map(imageMapper::map)
        }
    }

    fun getTrailers(movieId: Int): Flow<List<Video>> {
        return movieRepository.getVideos(movieId).map {
            it.map(trailerMapper::map)
        }
    }

    fun getCollection(collectionId:Int):Flow<Collection>{
        return movieRepository.getCollection(collectionId).map(collectionMapper::map)
    }
}