package com.example.cinemates.ui.details.actor

import androidx.lifecycle.*
import com.example.cinemates.model.Person
import com.example.cinemates.repository.ActorRepository
import com.example.cinemates.repository.MovieRepository
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
    private val movieRepository: MovieRepository,
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
            movieRepository.getMoviesByActor(it.id.toString())
        } ?: emptyFlow()
    }

    val images = actor.flatMapLatest { actor ->
        actor?.let {
            actorRepository.getImages(it.id)
        } ?: emptyFlow()
    }



}