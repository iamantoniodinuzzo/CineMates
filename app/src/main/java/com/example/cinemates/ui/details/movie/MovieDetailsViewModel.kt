package com.example.cinemates.ui.details.movie

import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.model.Collection
import com.example.cinemates.repository.MovieRepository
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

    val partsOfCollection: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())


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
            movieRepository.getMovieDetails(movieId).collectLatest { movie ->
                _selectedMovie.value = movie
                checkIfMovieIsAPartOfACollection(movie.belongs_to_collection)
            }
        }
    }


    private fun checkIfMovieIsAPartOfACollection(
        belongs_to_collection: Collection?
    ) {
        if (belongs_to_collection != null)
            getMoviesBelongCollection(belongs_to_collection.id)
    }

    val similarMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getSimilarMovies(it.id)
        } ?: emptyFlow()
    }

    val recommendedMovies = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getRecommendedMovies(it.id)
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
            movieRepository.getMovieCast(it.id)
        } ?: emptyFlow()
    }

    val crew = selectedMovie.flatMapLatest { movie ->
        movie?.let {
            movieRepository.getMovieCrew(it.id)

        } ?: emptyFlow()
    }


    private fun getMoviesBelongCollection(collectionId: Int) = viewModelScope.launch {
        movieRepository.getCollection(collectionId).collect {
            partsOfCollection.value = it.parts
        }
    }

}