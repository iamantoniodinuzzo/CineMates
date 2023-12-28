package com.indisparte.saved.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.base.Media
import com.indisparte.movie_data.repository.MovieRepository
import com.indisparte.network.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 *@author Antonio Di Nuzzo
 */
@HiltViewModel
class HistoryViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {
    private val LOG = Timber.tag(HistoryViewModel::class.java.simpleName)

    private val _history = MutableStateFlow<Result<List<Media>>>(Result.Success(emptyList()))
    val history: StateFlow<Result<List<Media>>> get() = _history



     fun getMovieHistory() {
        viewModelScope.launch {
            movieRepository.getAllSeenMovies().collectLatest {
                _history.emit(it)
            }
        }
    }




}