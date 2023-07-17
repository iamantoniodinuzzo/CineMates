package com.indisparte.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.model.entity.MovieDetails
import com.indisparte.movie.repository.MovieRepository
import com.indisparte.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 */
@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _selectedMovie = MutableSharedFlow<Resource<MovieDetails?>>()
    val selectedMovie: SharedFlow<Resource<MovieDetails?>> get() = _selectedMovie.asSharedFlow()

    /**
     * Retrieves additional information about the selected movie
     */
    fun onDetailsFragmentReady(id: Int) =
        getMovieDetails(id)


    /*
        Through the film id, it retrieves the details and checks if the film is part of a collection.
        If it is successful, it initializes the variable containing the parts of the collection
     */
    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _selectedMovie.emit(Resource.Loading())
            try {
                movieRepository.getDetails(movieId).collectLatest {
                    Timber.tag("MovieDetailsViewModel").d("Movie details: ${it.data.toString()}")
                    _selectedMovie.emit(Resource.Success(it.data))
                }
            } catch (e: Exception) {
                _selectedMovie.emit(Resource.Error(e))
            }
        }
    }


}
