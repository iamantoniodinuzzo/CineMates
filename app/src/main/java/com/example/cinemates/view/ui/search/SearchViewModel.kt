package com.example.cinemates.view.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.Cast
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


private const val TAG = "SearchViewModel"

/**
 * Shared between [SearchMovieFragment] [SearchActorFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
@HiltViewModel
class SearchViewModel
@Inject
constructor(private val movieRepository: MovieRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()
    val query: LiveData<String> get() = _query

    private val _queriedMovies = MutableLiveData<List<Movie>>()
    val queriedMovies: LiveData<List<Movie>> get() = _queriedMovies

    private val _queriedActors = MutableLiveData<List<Cast>>()
    val queriedActors: LiveData<List<Cast>> get() = _queriedActors

    init {
        clearQuery()
    }

    fun setQuery(query: String) {
        _query.value = query
        searchMovies(query)
        searchActors(query)
    }

    private fun searchActors(query: String) = viewModelScope.launch {
        movieRepository.getMoviesBySearch(query).let { response ->

            if (response.isSuccessful) {
                _queriedMovies.value = response.body()?.results
            } else {
                Log.d(TAG, "getQueriesMovies Error: ${response.code()}")
            }

        }
    }

    private fun searchMovies(query: String) = viewModelScope.launch {
        movieRepository.getPeoplesBySearch(query).let { response ->

            if (response.isSuccessful) {
                _queriedActors.value = response.body()?.results
            } else {
                Log.d(TAG, "getQueriedActors Error: ${response.code()}")
            }
        }
    }

    private fun clearQuery() {
        _query.value = ""
    }

}