package com.example.cinemates.domain.usecases.search.getSearched

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.movie.MovieToMediaMapper
import com.example.cinemates.domain.model.Media
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
    private val mediaMapper: MovieToMediaMapper
) {

    operator fun invoke(query: String): Flow<List<Media>> {
        return movieRepository.getBySearch(query).map {
            it.map(mediaMapper::map)
        }
    }
}