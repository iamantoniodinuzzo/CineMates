package com.example.cinemates.view.ui.details.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Person
import com.example.cinemates.repositories.ActorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */

@HiltViewModel
class ActorDetailsViewModel
@Inject
constructor(
    private val actorRepository: ActorRepository,
) : ViewModel() {

    private val _actor = MutableStateFlow<Person?>(null)
    val actor: Flow<Person?> get() = _actor

    fun onDetailsFragmentReady(personId: Int) {
        getActorDetails(personId)
    }

    private fun getActorDetails(id: Int) {
        viewModelScope.launch {
            actorRepository.getActorDetails(id).collectLatest { person ->
                _actor.value = person
            }
        }
    }

    val movies = actor.flatMapLatest { actor ->
        actor?.let {
            actorRepository.getMoviesByActor(it.id)
        } ?: emptyFlow()
    }

    val images = actor.flatMapLatest { actor ->
        actor?.let {
            actorRepository.getImages(it.id)
        } ?: emptyFlow()
    }

    val cast = actor.flatMapLatest { actor ->
        actor?.let {
            actorRepository.getActorCharacters(it.id)
        } ?: emptyFlow()
    }

    val crew = actor.flatMapLatest { actor->
        actor?.let {
            actorRepository.getActorCrewWork(it.id)
        }?: emptyFlow()
    }


}