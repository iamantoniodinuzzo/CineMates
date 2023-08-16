package com.indisparte.media_discover.custom_filters

import androidx.lifecycle.ViewModel
import com.github.ajalt.timberkt.Timber
import com.indisparte.discover.repository.MovieDiscoverRepository
import com.indisparte.discover.util.MediaDiscoverFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class FilterableFragmentViewModel
@Inject
constructor(
    private val discoverRepository: MovieDiscoverRepository,
) : ViewModel() {

    private val LOG = Timber.tag(FilterableFragmentViewModel::class.java.simpleName)

    private val _mediaDiscoverFilter = MutableStateFlow(MediaDiscoverFilter())

    val mediaDiscoverFilter: StateFlow<MediaDiscoverFilter> get() = _mediaDiscoverFilter

    //    private val _filteredFilms = MutableStateFlow<Result<List<Movie>>>(Result.Success(emptyList()))
    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredFilms = mediaDiscoverFilter.flatMapLatest {
        LOG.d("Apply $it")
        discoverRepository.discoverMoviesByFilter(it)
    }


    fun updateFilter(mediaDiscoverFilter: MediaDiscoverFilter) {
        LOG.d("Update filter..")
        _mediaDiscoverFilter.update { current ->
            current.copy(
                sortBy = mediaDiscoverFilter.sortBy,
                withGenresIds = mediaDiscoverFilter.withGenresIds
            )
        }
    }

    fun clearFilter() {
        LOG.d("Clear filter..")
        _mediaDiscoverFilter.value = MediaDiscoverFilter()
    }


}



