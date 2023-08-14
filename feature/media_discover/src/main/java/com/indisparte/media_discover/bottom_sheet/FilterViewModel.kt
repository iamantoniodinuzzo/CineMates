package com.indisparte.media_discover.bottom_sheet

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.genre.repository.GenreRepository
import com.indisparte.model.entity.Genre
import com.indisparte.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilterViewModel
@Inject
constructor(
    savedStateHandle: SavedStateHandle,
    private val genreRepository: GenreRepository,
) : ViewModel() {
    private val _movieGenres = MutableStateFlow<Result<List<Genre>>>(Result.Success(emptyList()))
    val movieGenres: StateFlow<Result<List<Genre>>> get() = _movieGenres

    init {
        genMovieGenres()
    }

    private fun genMovieGenres() {
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

}