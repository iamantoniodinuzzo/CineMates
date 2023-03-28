package com.example.cinemates.ui.search

import androidx.lifecycle.ViewModel
import com.example.cinemates.repositories.ActorRepository
import com.example.cinemates.repositories.MovieRepository
import com.example.cinemates.repositories.TvShowRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
        _query.value = query.trim()
    }

    val searchedActors = query.flatMapLatest { query ->
        query?.let {
            actorRepository.getPeoplesBySearch(it)
        } ?: emptyFlow()
    }

    val searchedMovies = query.flatMapLatest { query ->
        query?.let {
            movieRepository.getBySearch(it)
        } ?: emptyFlow()
    }

    val searchedTvShow = query.flatMapLatest { query ->
        query?.let {
            tvShowRepository.getBySearch(it)
        } ?: emptyFlow()

    }


    private fun clearQuery() {
        _query.value = ""
    }

}