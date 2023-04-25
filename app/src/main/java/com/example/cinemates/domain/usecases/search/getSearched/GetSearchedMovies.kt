package com.example.cinemates.domain.usecases.search.getSearched

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.movie.mapToMedia
import com.example.cinemates.domain.model.Media
import dagger.multibindings.IntKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetSearchedMovies
@Inject
constructor(
    private val movieRepository: MovieRepository,
) {

    operator fun invoke(query: String): Flow<List<Media>> {
        return movieRepository.getBySearch(query).map { movieListDTO ->
            movieListDTO.map { it.mapToMedia() }
        }
    }
}