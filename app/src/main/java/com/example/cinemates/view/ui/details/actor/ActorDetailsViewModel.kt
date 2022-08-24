package com.example.cinemates.view.ui.details.actor

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Actor
import com.example.cinemates.model.data.Movie
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
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _actor = MutableLiveData<Actor>()
    val actor: LiveData<Actor> get() = _actor

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

    fun setActorDetails(personId: Int) {
        getActorDetails(personId)
        getMoviesByActor(personId)
    }

    private fun getMoviesByActor(id: Int) = viewModelScope.launch {
        movieRepository.getMoviesByActor(id.toString()).let { response ->

              if(response.isSuccessful){
                  _movies.postValue(response.body())
              }else{
                  Log.d(TAG, "getMoviesByActor Error: ${response.code()}")
              }
        }
    }


    private fun getActorDetails(id: Int) = viewModelScope.launch {
        movieRepository.getActorDetails(id).let { response ->

              if(response.isSuccessful){
                  _actor.postValue(response.body())
              }else{
                  Log.d(TAG, "getActorDetails Error: ${response.code()}")
              }
        }
    }
}