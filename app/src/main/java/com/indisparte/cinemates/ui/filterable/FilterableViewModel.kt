package com.indisparte.cinemates.ui.filterable

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indisparte.cinemates.domain.model.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


private const val TAG = "FilterableViewModel"

/**
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
@HiltViewModel
class FilterableViewModel
@Inject
constructor(
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> get() = _movies

//    fun setFilter(filter: MediaFilter) = getFilteredMovies(filter)

    /* private fun getFilteredMovies(filter: MediaFilter) {
         movieRepositoryImpl.getDiscoverable(filter)
             .mapLatest { movies ->
                 _movies.postValue(movies)
             }
             .launchIn(viewModelScope)
     }*/

    fun getRandomMovie(): Movie? {
        return movies.value?.random()
    }


}