package com.indisparte.movie_details.fragments.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.list.MediaList
import com.indisparte.list.repository.MediaListRepository
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.repository.MovieRepository
import com.indisparte.network.Result
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
class ManageMediaViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
    private val mediaListRepository: MediaListRepository,
) : ViewModel() {

    private val tag = Timber.tag(ManageMediaViewModel::class.java.simpleName)

    private val _lists = MutableStateFlow<Result<List<MediaList>>>(Result.Success(emptyList()))
    val list: StateFlow<Result<List<MediaList>>> get() = _lists

    init {
        getAllPersonaList()
    }

    private fun getAllPersonaList() {
        viewModelScope.launch {
            mediaListRepository.getAllList().collectLatest {
                _lists.emit(it)
            }
        }
    }

    fun setMovieAsFavorite(movie: Movie) {
        viewModelScope.launch {
            movieRepository.setMovieAsFavorite(movie).collectLatest {
                tag
                    .d("Movie inserito tra i preferiti: $it")
            }
        }
    }

    fun removeMovieFromFavorite(movie: Movie) {
        viewModelScope.launch {
            movieRepository.removeMovieFromFavorite(movie).collectLatest {
                tag
                    .d("Movie rimosso da i preferiti: $it")
            }
        }
    }

    fun setMovieAsToSee(movie: Movie) {
        viewModelScope.launch {
            movieRepository.setMovieAsToSee(movie).collectLatest {
                tag
                    .d("Movie inserito tra i da vedere: $it")
            }
        }
    }

    fun removeMovieFromToSee(movie: Movie) {
        viewModelScope.launch {
            movieRepository.removeMovieFromToSee(movie).collectLatest {
                tag
                    .d("Movie rimosso dai da vedere: $it")
            }
        }
    }

    fun setMovieAsSeen(movie: Movie) {
        viewModelScope.launch {
            movieRepository.setMovieAsSeen(movie).collectLatest {
                tag
                    .d("Movie inserito tra i visti: $it")
            }
        }
    }

    fun removeMovieFromSeen(movie: Movie) {
        viewModelScope.launch {
            movieRepository.removeMovieFromSeen(movie).collectLatest {
                tag
                    .d("Movie rimosso dai visti: $it")
            }
        }
    }


}