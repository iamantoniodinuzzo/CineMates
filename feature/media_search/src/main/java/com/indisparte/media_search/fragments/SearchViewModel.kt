package com.indisparte.media_search.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.media_search.repository.MediaSearchRepository
import com.indisparte.movie_data.Movie
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * @author Antonio Di Nuzzo
 */
@HiltViewModel
class SearchViewModel
@Inject
constructor(
    private val mediaSearchRepository: MediaSearchRepository,
) : ViewModel() {

    private val _query = MutableStateFlow("")
    private val query: StateFlow<String> get() = _query

    private val _moviesBySearch = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    val moviesBySearch: StateFlow<Result<List<Movie>>> get() = _moviesBySearch

    init {
        observeQueryChanges()
    }

    fun updateQuery(query: String) {
        _query.value = query
    }

    private fun observeQueryChanges() {
        viewModelScope.launch {
            _query.collectLatest {
                if (it.isNotEmpty()) {
                    //search media
                    searchMovies(it)
                } else {
                    //clean all results
                    emptyValues()
                }
            }

        }
    }

    private fun emptyValues() {
        _moviesBySearch.value = Result.Success(emptyList())
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            _moviesBySearch.emit(Result.Loading)
            try {
                mediaSearchRepository.searchMovieByTitle(query).collectLatest { result ->
                    _moviesBySearch.emit(result)

                }
            } catch (e: CineMatesExceptions) {
                _moviesBySearch.emit(Result.Error(e))
            }
        }
    }


}