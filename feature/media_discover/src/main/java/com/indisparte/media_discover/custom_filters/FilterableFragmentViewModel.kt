package com.indisparte.media_discover.custom_filters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.discover.repository.MovieDiscoverRepository
import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.model.entity.Movie
import com.indisparte.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterableFragmentViewModel
@Inject
constructor(
    private val discoverRepository: MovieDiscoverRepository,
) : ViewModel() {

    private val _filteredFilms = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    val filteredFilms: StateFlow<Result<List<Movie>>> get() = _filteredFilms

    private val _mediaDiscoverFilter = MutableStateFlow(MediaDiscoverFilter())
    val mediaDiscoverFilter: StateFlow<MediaDiscoverFilter> get() = _mediaDiscoverFilter

    init {
        observeMediaFilterChanges()
    }

    private fun observeMediaFilterChanges() {
        viewModelScope.launch {
            mediaDiscoverFilter.collectLatest {
                getFilteredFilms(it)
            }
        }
    }

    fun updateFilter(mediaDiscoverFilter: MediaDiscoverFilter) {
        _mediaDiscoverFilter.value = mediaDiscoverFilter
    }

    private fun getFilteredFilms(mediaDiscoverFilter: MediaDiscoverFilter) {
        viewModelScope.launch {
            _filteredFilms.emit(Result.Loading)
            try {
                discoverRepository.discoverMoviesByFilter(mediaDiscoverFilter).collectLatest {
                    _filteredFilms.emit(it)
                }
            } catch (e: Exception) {
                _filteredFilms.emit(Result.Error(e))
            }
        }
    }


}