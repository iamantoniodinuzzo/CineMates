package com.example.cinemates.view.ui.details.actor

import androidx.lifecycle.*
import com.example.cinemates.model.entities.Image
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.entities.Person
import com.example.cinemates.model.entities.TvShow
import com.example.cinemates.model.repository.ActorRepository
import com.example.cinemates.model.repository.MovieRepository
import com.example.cinemates.model.repository.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
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

    private val _actor = MutableLiveData<Person>()
    val actor: LiveData<Person> get() = _actor

    val movies: MutableLiveData<List<Movie>> =
        Transformations.switchMap(_actor) { actor ->
            movieRepository.getMoviesByActor(actor.id.toString()).asLiveData()
        }as MutableLiveData<List<Movie>>

    val images: MutableLiveData<List<Image>> =
        Transformations.switchMap(_actor) { actor ->
            actorRepository.getImages(actor.id).asLiveData()
        }as MutableLiveData<List<Image>>

    fun onDetailsFragmentReady(personId: Int) {
        getActorDetails(personId)
    }

     fun onDestroyFragment() {
        super.onCleared()
        movies.value = listOf()
        images.value = listOf()
    }


    private fun getActorDetails(id: Int) = viewModelScope.launch {
        try {
            actorRepository.getActorDetails(id).collectLatest { person ->

                _actor.postValue(person)

            }
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }

    }
}