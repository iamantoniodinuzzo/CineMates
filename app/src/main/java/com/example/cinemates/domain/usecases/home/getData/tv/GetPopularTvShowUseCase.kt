package com.example.cinemates.domain.usecases.home.getData.tv

import com.example.cinemates.data.remote.repository.TvShowRepository
import com.example.cinemates.domain.mapper.tv.TvToMediaMapper
import com.example.cinemates.domain.model.Media
import com.example.cinemates.domain.model.TvShow
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
    private val tvToMediaMapper: TvToMediaMapper
) {

    suspend operator fun invoke(): Flow<List<Media>> {
        return tvShowRepository .getSpecificTVList("popular").map {
            it.map(tvToMediaMapper::map)
        }
    }

}