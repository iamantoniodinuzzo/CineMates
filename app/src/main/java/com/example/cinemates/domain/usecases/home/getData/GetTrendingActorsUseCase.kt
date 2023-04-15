package com.example.cinemates.domain.usecases.home.getData

import com.example.cinemates.data.remote.repository.ActorRepository
import com.example.cinemates.domain.mapper.credits.CastToMediaMapper
import com.example.cinemates.domain.model.Media
import com.example.cinemates.util.MediaType
import com.example.cinemates.util.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetTrendingActorsUseCase
@Inject
constructor(
    private val actorRepository: ActorRepository,
    private val castToMediaMapper: CastToMediaMapper
) {
    operator fun invoke(): Flow<List<Media>> {
        return actorRepository.getTrendingPerson(MediaType.PERSON.value, TimeWindow.WEEK.value)
            .map {
                it.map(castToMediaMapper::map)
            }
    }

}