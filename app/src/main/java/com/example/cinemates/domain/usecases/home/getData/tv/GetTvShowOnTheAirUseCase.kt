package com.example.cinemates.domain.usecases.home.getData.tv

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.tv.mapToMedia
import com.example.cinemates.domain.model.common.Media
import com.example.cinemates.util.MediaListSpecification
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetTvShowOnTheAirUseCase
@Inject
constructor(
    private val tvShowRepository: TvShowRepository,
) {

     operator fun invoke(): Flow<List<Media>> {
        return tvShowRepository.getSpecificTVList(MediaListSpecification.TV_ON_THE_AIR).map { tvShowDTOList ->
            tvShowDTOList.map { it.mapToMedia() }
        }
    }

}