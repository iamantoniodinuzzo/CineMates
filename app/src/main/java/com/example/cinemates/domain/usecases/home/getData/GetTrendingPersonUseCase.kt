package com.example.cinemates.domain.usecases.home.getData

import com.example.cinemates.data.remote.repository.ActorRepository
import com.example.cinemates.domain.mapper.credits.PersonDetailsMapper
import com.example.cinemates.domain.mapper.credits.PersonMapper
import com.example.cinemates.domain.model.Person
import com.example.cinemates.util.MediaType
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
    private val personMapper: PersonMapper
) {
    operator fun invoke(): Flow<List<Person>> {
        return actorRepository.getTrendingPerson(MediaType.PERSON.value, TimeWindow.WEEK.value)
            .map {
                it.map(personMapper::map)
            }
    }

}