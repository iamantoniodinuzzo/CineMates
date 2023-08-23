package com.indisparte.media_discover.filterable_fragment.movie

import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.indisparte.discover.repository.MovieDiscoverRepository
import com.indisparte.discover.util.MediaDiscoverFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilterableMovieFragmentViewModel
@Inject
constructor(
    private val discoverRepository: MovieDiscoverRepository,
) : ViewModel() {

    private val LOG = Timber.tag(FilterableMovieFragmentViewModel::class.java.simpleName)

    private val _selectedMovieFilter = MutableStateFlow(MediaDiscoverFilter())

    val selectedMovieFilter: StateFlow<MediaDiscoverFilter> get() = _selectedMovieFilter

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredFilms = _selectedMovieFilter.flatMapLatest {
        LOG.d("Apply $it")
        discoverRepository.discoverMoviesByFilter(it)
    }


    override fun onCleared() {
        super.onCleared()
        clearFilter()
    }

    fun updateFilter(mediaDiscoverFilter: MediaDiscoverFilter) {
        LOG.d("Update filter..")
        _selectedMovieFilter.update { current ->
            current.copy(
                sortBy = mediaDiscoverFilter.sortBy,
                withGenresIds = mediaDiscoverFilter.withGenresIds
            )
        }
    }

    fun clearFilter() {
        LOG.d("Clear filter..")
        _selectedMovieFilter.value = MediaDiscoverFilter()
    }


}



