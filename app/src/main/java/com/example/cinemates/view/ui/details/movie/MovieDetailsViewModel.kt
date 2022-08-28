package com.example.cinemates.view.ui.details.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemates.model.data.*
import com.example.cinemates.model.data.Collection
import com.example.cinemates.model.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Is shared between  [MovieCastFragment] [MovieImagesFragment] [MovieInfoFragment] [CollectionDialogFragment]
 * @author Antonio Di Nuzzo
 * Created 24/08/2022
 */
private const val TAG = "MovieDetailsViewModel"

@HiltViewModel
class MovieDetailsViewModel
@Inject
constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> get() = _selectedMovie

    private val _similarMovies = MutableLiveData<List<Movie>>()
    val similarMovies: LiveData<List<Movie>> get() = _similarMovies

    private val _videos = MutableLiveData<List<Video>>()
    val videos: LiveData<List<Video>> get() = _videos

    private val _imagesResponse = MutableLiveData<ImagesResponse>()
    val imagesResponse: LiveData<ImagesResponse> get() = _imagesResponse

    private val _cast = MutableLiveData<List<Cast>>()
    val cast: LiveData<List<Cast>> get() = _cast

    private val _crew = MutableLiveData<List<Crew>>()
    val crew: LiveData<List<Crew>> get() = _crew

    private val _moviesBelongsCollection = MutableLiveData<List<Movie>>()
    val moviesBelongsCollection: LiveData<List<Movie>> get() = _moviesBelongsCollection

    fun setSelectedMovie(movie: Movie) {
        getMovieDetails(movie.id)
        getSimilarMovies(movie.id)
        getMovieVideos(movie.id)
        getMovieImages(movie.id)
        getMovieCredits(movie.id)

    }

    fun setFavorite() {
        selectedMovie.value?.setFavorite()
    }

    fun setPersonalStatus(status: PersonalStatus){
        selectedMovie.value?.setPersonalStatus(status)
    }

    private fun getMoviesBelongsCollection(collectionId: Int) = viewModelScope.launch {
        movieRepository.getCollection(collectionId).let { response ->

            if (response.isSuccessful) {
                val collection: Collection? = response.body()
                _moviesBelongsCollection.value = collection?.parts
            } else {
                Log.d(TAG, "getMoviesBelongsCollection Error: ${response.code()}")
                _moviesBelongsCollection.value = listOf()
            }
        }
    }

    private fun getMovieCredits(movieId: Int) = viewModelScope.launch {
        movieRepository.getMovieCredits(movieId).let { response ->

            if (response.isSuccessful) {
                _cast.value = response.body()?.cast
                _crew.value = response.body()?.crew
            } else {
                Log.d(TAG, "getMovieCast Error: ${response.code()}")
                _cast.value = listOf()
            }
        }
    }

    private fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        movieRepository.getMovieDetails(movieId).let { response ->

            if (response.isSuccessful) {
                _selectedMovie.value = response.body()
                checkIfBelongsToCollection()
            } else {
                Log.d(TAG, "getMovieDetails Error: ${response.code()}")
            }
        }

    }

    private fun checkIfBelongsToCollection() {
        if (selectedMovie.value!!.belongs_to_collection != null) {
            getMoviesBelongsCollection(selectedMovie.value!!.belongs_to_collection!!.id)
        }
    }

    private fun getMovieImages(movieId: Int) = viewModelScope.launch {
        movieRepository.getImages(movieId).let { response ->

            if (response.isSuccessful) {
                _imagesResponse.value = response.body()
            } else {
                Log.d(TAG, "getMovieImages Error: ${response.code()}")
            }
        }
    }

    private fun getMovieVideos(movieId: Int) = viewModelScope.launch {
        movieRepository.getVideos(movieId).let { response ->

            if (response.isSuccessful) {
                _videos.value = response.body()?.results
            } else {
                Log.d(TAG, "getMovieVideos Error: ${response.code()}")
                _videos.value = listOf()
            }
        }
    }

    private fun getSimilarMovies(movieId: Int) = viewModelScope.launch {
        movieRepository.getSimilar(movieId).let { response ->

            if (response.isSuccessful) {
                _similarMovies.value = response.body()?.results
            } else {
                Log.d(TAG, "getSimilarMovies Error: ${response.code()}")
                _similarMovies.value = listOf()
            }
        }
    }

}