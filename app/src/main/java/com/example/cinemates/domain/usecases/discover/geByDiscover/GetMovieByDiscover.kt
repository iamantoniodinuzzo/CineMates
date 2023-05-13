package com.example.cinemates.domain.usecases.discover.geByDiscover

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.domain.mapper.movie.mapToMedia
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.domain.model.common.MediaFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetMovieByDiscover
@Inject
constructor(
    private val movieRepository: MovieRepository,
) {
    operator fun invoke(mediaFilter: MediaFilter): Flow<List<Media>> {
        return movieRepository.getDiscoverable(mediaFilter)
            .map { movieDTOList -> movieDTOList.map { it.mapToMedia() } }
    }
}
