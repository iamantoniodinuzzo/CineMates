package com.example.cinemates.ui.details.movie

import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.data.remote.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MediaImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022
 */
private val TAG = MovieDetailsViewModel::class.simpleName

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepositoryImpl: MovieRepositoryImpl
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
            movieRepositoryImpl.getDetails(movieId).collectLatest { movie ->
                _selectedMovie.value = movie
            }
        }
    }

    val images =
        selectedMovie.flatMapLatest { movie ->
            movie?.let {
                movieRepositoryImpl.getImages(movie.id)
            } ?: emptyFlow()
        }

    val collection =
        selectedMovie.flatMapLatest { movie ->
            movie?.belongsToCollection?.let {
                movieRepositoryImpl.getCollection(it.id)
            } ?: emptyFlow()
        }

    val similarMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getSimilar(it.id)
        } ?: emptyFlow()
    }

    val recommendedMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getRecommended(it.id)
        } ?: emptyFlow()
    }

    val videos = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getVideos(it.id)
        } ?: emptyFlow()
    }

    val posters = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getPosters(it.id)
        } ?: emptyFlow()
    }

    val backdrops = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getBackdrops(it.id)
        } ?: emptyFlow()
    }

    val cast = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getCast(it.id)
        } ?: emptyFlow()
    }

    val crew = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepositoryImpl.getCrew(it.id)

        } ?: emptyFlow()
    }


}