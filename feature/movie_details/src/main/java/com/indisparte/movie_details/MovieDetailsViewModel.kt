package com.indisparte.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie.repository.MovieRepository
import com.indisparte.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MediaImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
@HiltViewModel
internal class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _selectedMovie = MutableStateFlow<Resource<MovieDetails?>>(Resource.Loading())
    val selectedMovie: StateFlow<Resource<MovieDetails?>> get() = _selectedMovie


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
            _selectedMovie.value = Resource.Loading()
            try {
                movieRepository.getDetails(movieId).collectLatest {
                    _selectedMovie.value = Resource.Success(it.data)
                }
            } catch (e: Exception) {
                _selectedMovie.value = Resource.Error(e)
            }

        }
    }


}