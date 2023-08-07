package com.indisparte.media_search.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.Timber
import com.indisparte.media_search.repository.MovieSearchRepository
import com.indisparte.model.entity.Movie
import com.indisparte.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val movieSearchRepository: MovieSearchRepository,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val query: StateFlow<String> get() = _query

    private val _moviesBySearch = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    val moviesBySearch: StateFlow<Result<List<Movie>>> get() = _moviesBySearch

    init {
        observeQueryChanges()
    }

    fun updateQuery(query: String) {
        Timber.tag("SearchViewModel").d("Query is: $query")
        _query.value = query
    }

    private fun observeQueryChanges() {
        viewModelScope.launch {
            query.collectLatest {
                if (it.isNotEmpty()){
                    searchMovies(it)
                }else{
                    emptyValues()
                }
            }

        }
    }

    private fun emptyValues() {
        Timber.tag("SearchViewModel").d("Empty values")
        _moviesBySearch.value = Result.Success(emptyList())
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            _moviesBySearch.emit(Result.Loading)
            try {
                movieSearchRepository.searchMovieByTitle(query).collectLatest { result ->
                    _moviesBySearch.emit(result)

                }
            } catch (e: Exception) {
                _moviesBySearch.emit(Result.Error(e))
            }
        }
    }


}