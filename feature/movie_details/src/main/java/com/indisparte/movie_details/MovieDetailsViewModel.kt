package com.indisparte.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.model.entity.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MediaImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
) : ViewModel() {

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: Flow<Movie?> get() = _selectedMovie


    /**
     * Retrieves additional information about the selected movie
     */
    fun onDetailsFragmentReady(id: Int) =
        getMovieDetails(id)

    /*
        Through the film id , it retrieves the details and checks if the film is part of a collection.
        If it is successful, it initializes the variable containing the parts of the collection
     */
    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {

        }
    }


}