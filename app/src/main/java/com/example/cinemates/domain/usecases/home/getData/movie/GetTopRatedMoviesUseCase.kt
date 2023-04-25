package com.example.cinemates.domain.usecases.home.getData.movie

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.movie.mapToMedia
import com.example.cinemates.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetTopRatedMoviesUseCase
@Inject
constructor(
    private val movieRepository: MovieRepository,
) {
    // TODO: Send specification
     operator fun invoke(): Flow<List<Media>> {
        return movieRepository.getSpecificMovieList("top_rated").map { movieDTOList ->
            movieDTOList.map { it.mapToMedia() }
        }
    }
}