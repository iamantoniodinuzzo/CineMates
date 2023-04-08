package com.example.cinemates.ui.details.movie

import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022
 */
private val TAG = MovieDetailsViewModel::class.simpleName

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository
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
            movieRepository.getDetails(movieId).collectLatest { movie ->
                _selectedMovie.value = movie
            }
        }
    }

    val collection =
        selectedMovie.flatMapLatest { movie ->
            movie?.belongsToCollection?.let {
                movieRepository.getCollection(it.id)
            } ?: emptyFlow()
        }

    val similarMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getSimilar(it.id)
        } ?: emptyFlow()
    }

    val recommendedMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getRecommended(it.id)
        } ?: emptyFlow()
    }

    val videos = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getVideos(it.id)
        } ?: emptyFlow()
    }

    val posters = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getPosters(it.id)
        } ?: emptyFlow()
    }

    val backdrops = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getBackdrops(it.id)
        } ?: emptyFlow()
    }

    val cast = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getCast(it.id)
        } ?: emptyFlow()
    }

    val crew = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getCrew(it.id)

        } ?: emptyFlow()
    }


}