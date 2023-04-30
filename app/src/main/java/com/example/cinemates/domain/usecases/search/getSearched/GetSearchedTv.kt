package com.example.cinemates.domain.usecases.search.getSearched

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.tv.mapToMedia
import com.example.cinemates.domain.model.common.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetSearchedTv
@Inject
constructor(
    private val tvShowRepository: TvShowRepository,
) {
    operator fun invoke(query: String): Flow<List<Media>> {
        return tvShowRepository.getBySearch(query).map { tvShowDTOList ->
            tvShowDTOList.map { it.mapToMedia() }
        }
    }
}