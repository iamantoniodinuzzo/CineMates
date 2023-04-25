package com.example.cinemates.domain.usecases.details.tv

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.tv.mapToSeasonDetails
import com.example.cinemates.domain.model.SeasonDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetSeasonDetailsUseCase
@Inject
constructor(
    private val tvShowRepository: TvShowRepository,
) {
    operator fun invoke(tvId: Int, seasonNumber: Int): Flow<SeasonDetails> {
        return tvShowRepository.getSeasonDetails(tvId, seasonNumber).map { it.mapToSeasonDetails() }
    }
}