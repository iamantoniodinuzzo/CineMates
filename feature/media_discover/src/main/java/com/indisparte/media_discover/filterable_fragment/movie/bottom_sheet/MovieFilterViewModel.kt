package com.indisparte.media_discover.filterable_fragment.movie.bottom_sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.discover.util.SortOptions
import com.indisparte.genre.repository.GenreRepository
import com.indisparte.common.Genre
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


@HiltViewModel
class MovieFilterViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val LOG = Timber.tag(MovieFilterViewModel::class.java.simpleName)
    private val _movieGenres = MutableStateFlow<Result<List<com.indisparte.common.Genre>>>(Result.Success(emptyList()))
    val movieGenres: StateFlow<Result<List<com.indisparte.common.Genre>>> get() = _movieGenres

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val sortType: SortOptions? = SortOptions.DescendingPopularity,
        val genresId: Set<Int>? = emptySet(),
        val clearAllFilters: Boolean = false,
        val applyAllFilters: Boolean = false,
    ) {
        val filterCount: Int
            get() {
                val sortCount = if (sortType == SortOptions.DescendingPopularity) 0 else 1
                val filterCount = genresId?.size ?: 0
                return sortCount + filterCount
            }
    }


    init {
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

    fun setSortType(sortOption: SortOptions) {
        _uiState.update {
            it.copy(sortType = sortOption)
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
            it.copy(clearAllFilters = true, genresId = emptySet(), sortType = SortOptions.DescendingPopularity)
        }
    }

    fun applyFilters() {
        _uiState.update {
            LOG.d("Update filter")
            it.copy(applyAllFilters = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        LOG.d("View model pulito")
    }

}