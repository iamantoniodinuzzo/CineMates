package com.example.cinemates.view.ui.details.actor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.entities.Movie
import com.example.cinemates.model.entities.Person
import com.example.cinemates.model.repository.ActorRepository
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
private const val TAG = "ActorDetailsViewModel"

@HiltViewModel
class ActorDetailsViewModel
@Inject
constructor(
    private val actorRepository: ActorRepository,
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _actor = MutableLiveData<Person>()
    val actor: LiveData<Person> get() = _actor

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun setActorDetails(personId: Int) {
        getActorDetails(personId)
        getMoviesByActor(personId)
    }

    private fun getMoviesByActor(id: Int) = viewModelScope.launch {
        movieRepository.getMoviesByActor(id.toString()).let { response ->

            if (response.isSuccessful) {
                _movies.postValue(response.body()?.results)
            } else {
                Log.d(TAG, "getMoviesByActor Error: ${response.code()}")
                _movies.value = listOf()
            }
        }
    }


    private fun getActorDetails(id: Int) = viewModelScope.launch {
        actorRepository.getActorDetails(id).let { response ->

            if (response.isSuccessful) {
                _actor.postValue(response.body())
            } else {
                Log.d(TAG, "getActorDetails Error: ${response.code()}")
            }
        }
    }
}