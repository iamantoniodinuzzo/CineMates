package com.indisparte.movie_details.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indisparte.model.entity.Cast
import com.indisparte.model.entity.Movie
import com.indisparte.model.entity.MovieDetails
import com.indisparte.model.entity.Video
import com.indisparte.movie.repository.MovieRepository
import com.indisparte.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
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
    private val _videos = MutableStateFlow<Resource<List<Video>>?>(Resource.Loading())
    val videos: StateFlow<Resource<List<Video>>?> get() = _videos
    private val _cast = MutableStateFlow<Resource<List<Cast>>?>(Resource.Loading())
    val cast: StateFlow<Resource<List<Cast>>?> get() = _cast

    private val _similarMovies = MutableStateFlow<Resource<List<Movie>>?>(null)
    val similarMovies: StateFlow<Resource<List<Movie>>?> get() = _similarMovies

    init {
        observeSelectedMovie()
    }

    /**
     * Retrieves additional information about the selected movie
     */
    fun onDetailsFragmentReady(id: Int) =
        getMovieDetails(id)

    private fun observeSelectedMovie() {
        viewModelScope.launch {
            selectedMovie
                .filter { it is Resource.Success }
                .map { it as Resource.Success }
                .mapNotNull { it.data }
                .distinctUntilChanged()
                .collect { movieDetails ->
                    getVideos(movieDetails.id)
                    getCast(movieDetails.id)
                    getSimilar(movieDetails.id)
                }
        }
    }

    /*
        Through the film id, it retrieves the details and checks if the film is part of a collection.
        If it is successful, it initializes the variable containing the parts of the collection
     */
    private fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _selectedMovie.emit(Resource.Loading())
            try {
                movieRepository.getDetails(movieId).collectLatest {
                    val movieDetails = it.data
                    Timber.tag("MovieDetailsViewModel")
                        .d("Movie details: ${movieDetails.toString()}")
                    _selectedMovie.emit(Resource.Success(movieDetails))
                }
            } catch (e: Exception) {
                _selectedMovie.emit(Resource.Error(e))
            }
        }
    }

    private fun getVideos(movieId: Int) {
        viewModelScope.launch {
            _videos.emit(Resource.Loading())
            try {
                movieRepository.getVideos(movieId).collectLatest {
                    Timber.tag("MovieDetailsViewModel").d("Movie videos ${it.data.toString()}")
                    it.data?.let { videos ->
                        _videos.emit(Resource.Success(videos))
                    } ?: _videos.emit(Resource.Success(emptyList()))
                }
            } catch (e: Exception) {
                _videos.emit(Resource.Error(e))
            }
        }
    }

    private fun getSimilar(movieId: Int) {
        viewModelScope.launch {
            _similarMovies.emit(Resource.Loading())
            try {
                movieRepository.getSimilar(movieId).collectLatest { resources ->
                    Timber.tag("MovieDetailsViewModel")
                        .d("Similar ${resources.data?.map { it.title }}")
                    resources.data?.let { similar ->
                        _similarMovies.emit(Resource.Success(similar))
                    } ?: _similarMovies.emit(Resource.Success(emptyList()))
                }
            } catch (e: Exception) {
                _similarMovies.emit(Resource.Error(e))
            }
        }
    }

    private fun getCast(movieId: Int) {
        viewModelScope.launch {
            _cast.emit(Resource.Loading())
            try {
                movieRepository.getCast(movieId).collectLatest {
                    Timber.tag("MovieDetailsViewModel").d("Movie cast ${it.data}")
                    it.data?.let { cast ->
                        _cast.emit(Resource.Success(cast))

                    } ?: _cast.emit(Resource.Success(emptyList()))
                }
            } catch (e: Exception) {
                _cast.emit(Resource.Error(e))
            }
        }
    }


}
