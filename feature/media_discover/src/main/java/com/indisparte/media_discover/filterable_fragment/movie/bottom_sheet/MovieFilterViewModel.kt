package com.indisparte.media_discover.filterable_fragment.movie.bottom_sheet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.common.Genre
import com.indisparte.filter.MovieSortOptions
import com.indisparte.genre.repository.GenreRepository
import com.indisparte.network.Result
import com.indisparte.network.error.CineMatesExceptions
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
    private val genreRepository: GenreRepository,
) : ViewModel() {

    private val LOG = Timber.tag(MovieFilterViewModel::class.java.simpleName)
    private val _movieGenres = MutableStateFlow<Result<List<Genre>>>(Result.Success(emptyList()))
    val movieGenres: StateFlow<Result<List<Genre>>> get() = _movieGenres

    private val _myFavGenres = MutableStateFlow<List<Genre>>(emptyList())
    val myFavGenres: StateFlow<List<Genre>> get() = _myFavGenres

    private val _uiState = MutableStateFlow(UiState())
    val uiState = _uiState.asStateFlow()

    data class UiState(
        val sortType: MovieSortOptions? = MovieSortOptions.DescendingPopularity,
        val genresId: Set<Int>? = null,
        val clearAllFilters: Boolean = false,
        val applyAllFilters: Boolean = false,
    ) {
        val filterCount: Int
            get() {
                val sortCount = if (sortType == MovieSortOptions.DescendingPopularity) 0 else 1
                val filterCount = genresId?.size ?: 0
                return sortCount + filterCount
            }
    }


    init {
        getMovieGenres()
        getMyFavGenres()
    }

    private fun getMyFavGenres() {
        viewModelScope.launch {
            genreRepository.getMyFavGenres().collectLatest {
                _myFavGenres.emit(it)
            }
        }
    }

    private fun getMovieGenres() {
        viewModelScope.launch {
            _movieGenres.emit(Result.Loading)
            try {
                genreRepository.getMovieGenreList().collectLatest {
                    _movieGenres.emit(it)
                }
            } catch (e: CineMatesExceptions) {
                _movieGenres.emit(Result.Error(e))
            }
        }
    }

    fun setSortType(sortOption: MovieSortOptions) {
        _uiState.update {
            it.copy(sortType = sortOption)
        }
    }

    fun selectGenre(genreId: Int) {
        _uiState.update {
            val genres = (it.genresId ?: mutableSetOf()).toMutableSet()
            genres.add(genreId)
            it.copy(genresId = genres)
        }
    }

    fun applyFavGenres() {
        _uiState.update { uiState ->
            val genres = (uiState.genresId ?: mutableSetOf()).toMutableSet()
            val myFavGenresId = myFavGenres.value.map { myFavGenre -> myFavGenre.id }
            genres.addAll(myFavGenresId)
            uiState.copy(genresId = genres)
        }
    }

    fun removeFavGenres() {
        _uiState.update { uiState ->
            val genres = (uiState.genresId ?: mutableSetOf()).toMutableSet()
            val myFavGenresId = myFavGenres.value.map { myFavGenre -> myFavGenre.id }
            genres.removeAll(myFavGenresId.toSet())
            uiState.copy(genresId = genres)
        }
    }

    fun deselectGenre(genreId: Int) {
        _uiState.update {
            val genres = (it.genresId ?: mutableSetOf()).toMutableSet()
            genres.remove(genreId)
            it.copy(genresId = genres)
        }
    }

    fun resetFilters() {
        _uiState.update {
            it.copy(
                clearAllFilters = true,
                genresId = null,
                sortType = MovieSortOptions.DescendingPopularity
            )
        }
    }

    fun applyFilters() {
        _uiState.update {
            LOG.d("Update filter")
            it.copy(applyAllFilters = true)
        }
    }


}