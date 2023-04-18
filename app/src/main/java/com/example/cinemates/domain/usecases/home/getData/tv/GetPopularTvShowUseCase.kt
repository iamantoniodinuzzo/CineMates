package com.example.cinemates.domain.usecases.home.getData.tv

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.tv.mapToMedia
import com.example.cinemates.domain.model.Media
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetPopularTvShowUseCase
@Inject
constructor(
    private val tvShowRepository: TvShowRepository,
) {

     operator fun invoke(): Flow<List<Media>> {
        return tvShowRepository.getSpecificTVList("popular").map { tvShowListDTO ->
            tvShowListDTO.map { it.mapToMedia() }
        }
    }

}