package com.example.cinemates.view.ui.details.movie

import android.util.Log
import androidx.lifecycle.*
import com.example.cinemates.model.*
import com.example.cinemates.model.Collection
import com.example.cinemates.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment]
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 24/08/2022
 */

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val TAG = MovieDetailsViewModel::class.simpleName
    //It was decided to use a MutableSharedFlow rather than a MutableStateFlow
    //because the latter involves an initial value that must be set.
    private val _selectedMovie = MutableSharedFlow<Movie>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val selectedMovie: Flow<Movie> = _selectedMovie.distinctUntilChanged()

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
        movieRepository.getMovieDetails(movieId)
            .mapLatest { movie ->
                _selectedMovie.tryEmit(movie)
                checkIfMovieIsAPartOfACollection(movie.belongs_to_collection)
            }
            .launchIn(viewModelScope)
    }

    private fun checkIfMovieIsAPartOfACollection(
        belongs_to_collection: Collection?
    ) {
        if (belongs_to_collection != null)
            getMoviesBelongCollection(belongs_to_collection.id)
    }

    val similarMovies = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getSimilarMovies(it.movie.id)
    }

    val recommendedMovies = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getRecommendedMovies(it.movie.id)
    }

    val videos = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getVideos(it.movie.id)
    }

    val posters = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getPosters(it.movie.id)
    }

    val backdrops = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getBackdrops(it.movie.id)
    }

    val cast = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getMovieCast(it.movie.id)
    }

    val crew = combine(
        selectedMovie
    ) { (query) ->
        SelectedMovie(query)
    }.flatMapLatest {
        movieRepository.getMovieCrew(it.movie.id)
    }


    private fun getMoviesBelongCollection(collectionId: Int) = viewModelScope.launch {
        movieRepository.getCollection(collectionId).collect {
            partsOfCollection.value = it.parts
        }
    }


}

private data class SelectedMovie(val movie: Movie)