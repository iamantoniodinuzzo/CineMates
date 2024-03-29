package com.example.cinemates.domain.usecases.home.getData

import com.example.cinemates.data.remote.repository.ActorRepository
import com.example.cinemates.domain.mapper.credits.mapToPerson
import com.example.cinemates.domain.model.credits.Person
import com.example.cinemates.util.TimeWindow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetTrendingPersonUseCase
@Inject
constructor(
    private val actorRepository: ActorRepository,
) {
     operator fun invoke(): Flow<List<Person>> {
        return actorRepository.getTrendingPerson(TimeWindow.WEEK).map { personDTOList ->
                personDTOList.map { it.mapToPerson() }
            }
    }

}