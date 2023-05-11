package com.example.cinemates.domain.usecases.discover.getGenres

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.mapToGenre
import com.example.cinemates.domain.model.common.Genre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetMovieGenresUseCase
@Inject
constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(): Flow<List<Genre>> {
        return movieRepository.getGenreList().map { genreDTOList ->
            genreDTOList.map { it.mapToGenre() }
        }
    }
}