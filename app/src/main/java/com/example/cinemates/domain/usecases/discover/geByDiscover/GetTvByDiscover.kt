package com.example.cinemates.domain.usecases.discover.geByDiscover

import com.example.cinemates.data.remote.repository.MovieRepository
import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.movie.mapToMedia
import com.example.cinemates.domain.mapper.tv.mapToMedia
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.domain.model.common.MediaFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetTvByDiscover
@Inject
constructor(
    private val tvShowRepository: TvShowRepository,
) {
    operator fun invoke(mediaFilter: MediaFilter): Flow<List<Media>> {
        return tvShowRepository.getDiscoverable(mediaFilter)
            .map { tvShowDTOList -> tvShowDTOList.map { it.mapToMedia() } }
    }
}
