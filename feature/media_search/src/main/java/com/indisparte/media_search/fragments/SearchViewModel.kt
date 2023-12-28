package com.indisparte.media_search.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.media_search.repository.MediaSearchRepository
import com.indisparte.movie_data.Movie
import com.indisparte.network.util.Result
import com.indisparte.network.exception.CineMatesException
import com.indisparte.tv.TvShow
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

    private val _tvShowBySearch =
        MutableStateFlow<Result<List<TvShow>>>(Result.Success(emptyList()))
    val tvShowBySearch: StateFlow<Result<List<TvShow>>> get() = _tvShowBySearch

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
                    searchTvShow(it)
                } else {
                    //clean all results
                    emptyValues()
                }
            }

        }
    }

    private fun searchTvShow(query: String) {
        viewModelScope.launch {
            _tvShowBySearch.emit(Result.Loading)
            try {
                mediaSearchRepository.searchTvByTitle(query).collectLatest { result ->
                    _tvShowBySearch.emit(result)

                }
            } catch (e: CineMatesException) {
                _tvShowBySearch.emit(Result.Error(e))
            }
        }
    }

    private fun emptyValues() {
        _moviesBySearch.value = Result.Success(emptyList())
        _tvShowBySearch.value = Result.Success(emptyList())
    }

    private fun searchMovies(query: String) {
        viewModelScope.launch {
            _moviesBySearch.emit(Result.Loading)
            try {
                mediaSearchRepository.searchMovieByTitle(query).collectLatest { result ->
                    _moviesBySearch.emit(result)

                }
            } catch (e: CineMatesException) {
                _moviesBySearch.emit(Result.Error(e))
            }
        }
    }


}