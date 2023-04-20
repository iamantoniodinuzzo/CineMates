package com.example.cinemates.ui.details.movie

import androidx.lifecycle.*
import com.example.cinemates.domain.model.Movie
import com.example.cinemates.domain.usecases.details.movie.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
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
            getMovieDetailsUseCase.getMovieDetails(movieId).collectLatest { movie ->
                _selectedMovie.value = movie
            }
        }
    }


    val collection =
        selectedMovie.flatMapLatest { movie ->
            movie?.belongsToCollection?.let {
                getMovieDetailsUseCase.getCollection(it.id)
            } ?: emptyFlow()
        }

    val similarMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            getMovieDetailsUseCase.getSimilarMovies(it.id)
        } ?: emptyFlow()
    }

    /*  val recommendedMovies = selectedMovie.flatMapLatest { movie ->
          movie?.let {
              getMovieDetailsUseCase.getRecommended(it.id)
          } ?: emptyFlow()
      }*/

    val videos = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            getMovieDetailsUseCase.getTrailers(it.id)
        } ?: emptyFlow()
    }

    val posters = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            getMovieDetailsUseCase.getPosters(it.id)
        } ?: emptyFlow()
    }

    val backdrops = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            getMovieDetailsUseCase.getBackdrops(it.id)
        } ?: emptyFlow()
    }

    val cast = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            getMovieDetailsUseCase.getMovieCast(it.id)
        } ?: emptyFlow()
    }

    val crew = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            getMovieDetailsUseCase.getMovieCrew(it.id)

        } ?: emptyFlow()
    }


}