package com.indisparte.media_discover.bottom_sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.discover.util.MediaDiscoverFilter
import com.indisparte.discover.util.SortOptions
import com.indisparte.genre.repository.GenreRepository
import com.indisparte.model.entity.Genre
import com.indisparte.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

private const val KEY_FILTER_DATA = "filter_data"

@HiltViewModel
class FilterViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val LOG = Timber.tag(FilterViewModel::class.java.simpleName)
    private val _movieGenres = MutableStateFlow<Result<List<Genre>>>(Result.Success(emptyList()))
    val movieGenres: StateFlow<Result<List<Genre>>> get() = _movieGenres

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val sortType: String? = SortOptions.POPULARITY.descendingOrder,
        val genresId: Set<Int>? = emptySet(),
        val clearAllFilters: Boolean = false,
        val applyAllFilters: Boolean = false,
    ) {
        val filterCount: Int
            get() {
                val sortCount = if (sortType == SortOptions.POPULARITY.descendingOrder) 0 else 1
                val filterCount = genresId?.size ?: 0
                return sortCount + filterCount
            }
    }


    init {
        savedStateHandle.get<MediaDiscoverFilter>(KEY_FILTER_DATA)?.let { filterData ->
            _uiState.update {
                LOG.d("Init block, update filter by savedStateHandle")
                it.copy(sortType = filterData.sortBy, genresId = filterData.withGenresIds)
            }
        }
        getMovieGenres()
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            _movieGenres.emit(Result.Loading)
            try {
                genreRepository.getMovieGenreList().collectLatest {
                    _movieGenres.emit(it)

                }
            } catch (e: Exception) {
                _movieGenres.emit(Result.Error(e))
            }
        }
    }

    fun setSortType(sortType: String) {
        _uiState.update {
            it.copy(sortType = sortType)
        }
    }

    fun selectGenre(genreId: Int) {
        _uiState.update {
            val genres = it.genresId?.toMutableSet()?.apply {
                add(genreId)
            }
            it.copy(genresId = genres)
        }
    }

    fun deselectGenre(genreId: Int) {
        _uiState.update {
            val genres = it.genresId?.toMutableSet()?.apply {
                remove(genreId)
            }
            it.copy(genresId = genres)
        }
    }

    fun resetFilters() {
        _uiState.update {
            it.copy(clearAllFilters = true, genresId = emptySet(), sortType = SortOptions.POPULARITY.descendingOrder)
        }
    }

    fun applyFilters() {
        _uiState.update {
            LOG.d("Update filter")
            it.copy(applyAllFilters = true)
        }
    }

}