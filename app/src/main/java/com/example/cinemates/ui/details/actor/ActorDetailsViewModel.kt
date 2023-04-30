package com.example.cinemates.ui.details.actor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.domain.model.credits.PersonDetails
import com.example.cinemates.domain.usecases.details.person.GetPersonDetailsUseCase
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
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase
) : ViewModel() {

    private val _personDetails = MutableStateFlow<PersonDetails?>(null)
    val personDetails: Flow<PersonDetails?> get() = _personDetails

    fun onDetailsFragmentReady(personId: Int) {
        getActorDetails(personId)
    }

    private fun getActorDetails(id: Int) {
        viewModelScope.launch {
            getPersonDetailsUseCase.getPersonDetails(id).collectLatest { person ->
                _personDetails.value = person
            }
        }
    }

    val movies = personDetails.flatMapLatest { actor ->
        actor?.let {
            getPersonDetailsUseCase.getPersonMovies(it.id)
        } ?: emptyFlow()
    }

    val images = personDetails.flatMapLatest { actor ->
        actor?.let {
            getPersonDetailsUseCase.getPersonImages(it.id)
        } ?: emptyFlow()
    }

  /*  val cast = personDetails.flatMapLatest { actor ->
        actor?.let {
            actorRepositoryImpl.getActorCharacters(it.id)
        } ?: emptyFlow()
    }

    val crew = personDetails.flatMapLatest { actor->
        actor?.let {
            actorRepositoryImpl.getActorCrewWork(it.id)
        }?: emptyFlow()
    }*/


}