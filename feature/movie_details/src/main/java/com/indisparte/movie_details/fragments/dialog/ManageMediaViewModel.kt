package com.indisparte.movie_details.fragments.dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.movie_data.Movie
import com.indisparte.movie_data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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
) : ViewModel() {


    fun setMovieAsFavorite(movie: Movie) {
        viewModelScope.launch {
            movieRepository.setMovieAsFavorite(movie).collectLatest {
                Timber.tag(ManageMediaViewModel::class.java.simpleName)
                    .d("Movie inserito tra i preferiti: $it")
            }
        }
    }

    fun removeMovieFromFavorite(movie: Movie){
        viewModelScope.launch {
            movieRepository.removeMovieFromFavorite(movie).collectLatest {
                Timber.tag(ManageMediaViewModel::class.java.simpleName)
                    .d("Movie rimosso da i preferiti: $it")
            }
        }
    }


}