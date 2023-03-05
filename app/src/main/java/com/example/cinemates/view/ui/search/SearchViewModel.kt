package com.example.cinemates.view.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.repository.ActorRepository
import com.example.cinemates.repository.MovieRepository
import com.example.cinemates.repository.TvShowRepository
import com.example.cinemates.util.Sort
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


private val TAG = SearchViewModel::class.simpleName

/**
 * Shared between [SearchFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
    private val actorRepository: ActorRepository,
    private val tvShowRepository: TvShowRepository
) : ViewModel() {

    private val _query = MutableStateFlow<String?>(null)
    val query: Flow<String?> get() = _query


    init {
        clearQuery()
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    val searchedActors = query.flatMapLatest { query ->
        query?.let {
            actorRepository.getPeoplesBySearch(it)
        } ?: emptyFlow()
    }

    val searchedMovies = query.flatMapLatest { query ->
        query?.let {
            movieRepository.getMoviesBySearch(it)
        } ?: emptyFlow()
    }

    val searchedTvShow = query.flatMapLatest { query ->
        query?.let {
            tvShowRepository.getTvShowBySearch(it)
        } ?: emptyFlow()

    }


    private fun clearQuery() {
        _query.value = ""
    }

}