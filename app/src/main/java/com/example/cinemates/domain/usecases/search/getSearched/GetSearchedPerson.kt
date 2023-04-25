package com.example.cinemates.domain.usecases.search.getSearched

import com.example.cinemates.data.remote.repository.ActorRepository
import com.example.cinemates.domain.mapper.credits.mapToPerson
import com.example.cinemates.domain.model.Person
import dagger.multibindings.IntKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
class GetSearchedPerson
@Inject
constructor(
    private val actorRepository: ActorRepository,
) {

    operator fun invoke(query: String): Flow<List<Person>> {
        return actorRepository.getPeoplesBySearch(query).map { personDTOList ->
            personDTOList.map { it.mapToPerson() }
        }
    }
}